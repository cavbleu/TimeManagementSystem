package ru.egartech.tmsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.model.dto.DepartmentDto;
import ru.egartech.tmsystem.model.dto.DepartmentSummaryDto;
import ru.egartech.tmsystem.model.entity.Department;
import ru.egartech.tmsystem.model.mapping.DepartmentMapper;
import ru.egartech.tmsystem.model.repository.DepartmentRepository;
import ru.egartech.tmsystem.model.dto.FilterDto;
import ru.egartech.tmsystem.model.dto.SettingsDto;
import ru.egartech.tmsystem.utils.SummaryFormatter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;
    private final SettingsService settingsService;
    private final DepartmentMapper mapper;

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
            SummaryFormatter.toSummaryDto(workTime, distractionTime, restTime, lunchTime,
                    departmentSummaryDto, department, filter,settings);

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
