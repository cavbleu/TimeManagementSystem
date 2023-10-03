package ru.egartech.tmsystem.domain.department.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.domain.department.dto.DepartmentDto;
import ru.egartech.tmsystem.domain.department.dto.DepartmentSummaryDto;
import ru.egartech.tmsystem.domain.department.entity.Department;
import ru.egartech.tmsystem.domain.department.mapper.DepartmentMapper;
import ru.egartech.tmsystem.domain.department.repository.DepartmentRepository;
import ru.egartech.tmsystem.domain.distraction.service.DistractionService;
import ru.egartech.tmsystem.domain.filter.dto.FilterDto;
import ru.egartech.tmsystem.domain.rest.service.RestService;
import ru.egartech.tmsystem.domain.settings.dto.SettingsDto;
import ru.egartech.tmsystem.domain.settings.service.SettingsService;
import ru.egartech.tmsystem.domain.timesheet.service.TimeSheetService;
import ru.egartech.tmsystem.formatter.StatisticTimeFormatter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;
    private final TimeSheetService timeSheetService;
    private final DistractionService distractionService;
    private final SettingsService settingsService;
    private final RestService restService;
    private final DepartmentMapper mapper;
    private final StatisticTimeFormatter formatter;

    @Override
    public List<DepartmentDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public Optional<DepartmentDto> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public DepartmentDto save(DepartmentDto dto) {
        Department department = repository.save(mapper.toEntity(dto));
        return mapper.toDto(department);
    }

    @Override
    public DepartmentDto updateById(Long id, DepartmentDto dto) {
        return repository.findById(id)
                .map(d -> mapper.toDto(repository.save(d)))
                .orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<DepartmentSummaryDto> departmentsSummary(FilterDto filter) {

        List<DepartmentSummaryDto> departmentsSummary = new ArrayList<>();
        List<DepartmentDto> departments = findAll();
        SettingsDto settings = settingsService.findByCurrentSettingsProfile();

        for (DepartmentDto department : departments) {

            DepartmentSummaryDto departmentSummaryDto = new DepartmentSummaryDto();
            long workTime = departmentWorkTimeByDate(filter, department.getName());
            long distractionTime = departmentDistractionTimeByDate(filter, department.getName());
            long restTime = departmentRestTimeByDate(filter, department.getName());
            long lunchTime = departmentLunchTimeByDate(filter, department.getName());
            long productiveTime = workTime - distractionTime - restTime - lunchTime;
            long days = Duration.between(filter.getStartPeriod(), filter.getEndPeriod()).toDays();
            long overTime = workTime - settings.getDefaultWorkTime() * days;
            long overTimeMinutes = overTime > 0 ? overTime : -overTime;

            double workTimePercent = (double) (workTime * 100) / settings.getDefaultWorkTime() * days;
            double productiveTimePercent = (double) (productiveTime * 100) / workTime;
            double distractionTimePercent = (double) (distractionTime * 100) / workTime;
            double restTimePercent = (double) (restTime * 100) / workTime;
            double lunchTimePercent = (double) (lunchTime * 100) / workTime;
            double overTimePercent = (double) (overTime * 100) / settings.getDefaultWorkTime() * days;
            overTime = overTime > 0 ? overTimeMinutes : -overTime;

            departmentSummaryDto.setName(department.getName());
            departmentSummaryDto.setWorkTime(formatter.format(workTime, workTimePercent));
            departmentSummaryDto.setProductiveTime(formatter.format(productiveTime, productiveTimePercent));
            departmentSummaryDto.setDistractionTime(formatter.format(distractionTime, distractionTimePercent));
            departmentSummaryDto.setRestTime(formatter.format(restTime, restTimePercent));
            departmentSummaryDto.setLunchTime(formatter.format(lunchTime, lunchTimePercent));
            departmentSummaryDto.setOverTime(formatter.format(overTime, overTimeMinutes, overTimePercent));

            departmentsSummary.add(departmentSummaryDto);
        }

        return departmentsSummary;
    }

    @Override
    public long departmentWorkTimeByDate(FilterDto filter, String departmentName) {
        return repository.departmentWorkTimeByPeriod(filter.getStartPeriod().toLocalDate(),
                filter.getEndPeriod().toLocalDate(), departmentName);
    }

    @Override
    public long departmentDistractionTimeByDate(FilterDto filter, String departmentName) {
        return repository.departmentDistractionTimeByPeriod(filter.getStartPeriod().toLocalDate(),
                filter.getEndPeriod().toLocalDate(), departmentName);
    }

    @Override
    public long departmentRestTimeByDate(FilterDto filter, String departmentName) {
        return repository.departmentRestTimeByPeriod(filter.getStartPeriod().toLocalDate(),
                filter.getEndPeriod().toLocalDate(), departmentName);
    }

    @Override
    public long departmentLunchTimeByDate(FilterDto filter, String departmentName) {
        return repository.departmentLunchTimeByPeriod(filter.getStartPeriod().toLocalDate(),
                filter.getEndPeriod().toLocalDate(), departmentName);
    }

    @Override
    public long summaryWorkTimeByDate(FilterDto filter) {
        return repository.summaryWorkTimeByPeriod(filter.getStartPeriod().toLocalDate(),
                filter.getEndPeriod().toLocalDate());
    }

    @Override
    public long summaryDistractionTimeByDate(FilterDto filter) {
        return repository.summaryDistractionTimeByPeriod(filter.getStartPeriod().toLocalDate(),
                filter.getEndPeriod().toLocalDate());
    }

    @Override
    public long summaryRestTimeByDate(FilterDto filter) {
        return repository.summaryRestTimeByPeriod(filter.getStartPeriod().toLocalDate(),
                filter.getEndPeriod().toLocalDate());
    }

    @Override
    public long summaryLunchTimeByDate(FilterDto filter) {
        return repository.summaryLunchTimeByPeriod(filter.getStartPeriod().toLocalDate(),
                filter.getEndPeriod().toLocalDate());
    }
}
