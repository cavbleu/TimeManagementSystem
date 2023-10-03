package ru.egartech.tmsystem.domain.employee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.domain.employee.dto.EmployeeDto;
import ru.egartech.tmsystem.domain.employee.dto.EmployeeSummaryDto;
import ru.egartech.tmsystem.domain.employee.entity.Employee;
import ru.egartech.tmsystem.domain.employee.mapper.EmployeeMapper;
import ru.egartech.tmsystem.domain.employee.repository.EmployeeRepository;
import ru.egartech.tmsystem.domain.filter.dto.FilterDto;
import ru.egartech.tmsystem.domain.settings.dto.SettingsDto;
import ru.egartech.tmsystem.domain.settings.service.SettingsService;
import ru.egartech.tmsystem.utils.SummaryFormatter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class EmplolyeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;
    private final SettingsService settingsService;

    @Override
    public List<EmployeeDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public Optional<EmployeeDto> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public EmployeeDto save(EmployeeDto dto) {
        Employee employee = repository.save(mapper.toEntity(dto));
        return mapper.toDto(employee);
    }

    @Override
    public EmployeeDto updateById(Long id, EmployeeDto dto) {
        return repository.findById(id)
                .map(e -> mapper.toDto(repository.save(e)))
                .orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<EmployeeSummaryDto> employeesSummary(FilterDto filter) {
        List<EmployeeSummaryDto> employeeSummary = new ArrayList<>();
        List<EmployeeDto> empployees = findAll();
        SettingsDto settings = settingsService.findByCurrentSettingsProfile();

        for (EmployeeDto employee : empployees) {

            EmployeeSummaryDto employeeSummaryDto = new EmployeeSummaryDto();
            long workTime = employeeWorkTimeByDate(filter, employee.getName());
            long distractionTime = employeeDistractionTimeByDate(filter, employee.getName());
            long restTime = employeeRestTimeByDate(filter, employee.getName());
            long lunchTime = employeeLunchTimeByDate(filter, employee.getName());
            long productiveTime = workTime - distractionTime - restTime - lunchTime;
            long days = Duration.between(filter.getStartPeriod(), filter.getEndPeriod()).toDays();
            long overTime = workTime - settings.getDefaultWorkTime() * days;
            long overTimeMinutes = overTime > 0 ? overTime : -overTime;

            double workTimePercent = SummaryFormatter.percentFormat(workTime, settings.getDefaultWorkTime() * days);
            double productiveTimePercent = SummaryFormatter.percentFormat(productiveTime, workTime);
            double distractionTimePercent = SummaryFormatter.percentFormat(distractionTime, workTime);
            double restTimePercent = SummaryFormatter.percentFormat(restTime, workTime);
            double lunchTimePercent = SummaryFormatter.percentFormat(lunchTime, workTime);
            double overTimePercent = SummaryFormatter.percentFormat(overTime, settings.getDefaultWorkTime() * days);
            overTimePercent = overTimePercent > 0 ? overTimePercent : -overTime;

            employeeSummaryDto.setId(employee.getId());
            employeeSummaryDto.setName(employee.getName());
            employeeSummaryDto.setWorkTime(SummaryFormatter.statisticFormat(workTime, workTimePercent));
            employeeSummaryDto.setProductiveTime(SummaryFormatter.statisticFormat(productiveTime, productiveTimePercent));
            employeeSummaryDto.setDistractionTime(SummaryFormatter.statisticFormat(distractionTime, distractionTimePercent));
            employeeSummaryDto.setRestTime(SummaryFormatter.statisticFormat(restTime, restTimePercent));
            employeeSummaryDto.setLunchTime(SummaryFormatter.statisticFormat(lunchTime, lunchTimePercent));
            employeeSummaryDto.setOverTime(SummaryFormatter.statisticFormat(overTime, overTimeMinutes, overTimePercent));

            employeeSummary.add(employeeSummaryDto);
        }

        return employeeSummary;
    }

    @Override
    public long employeeWorkTimeByDate(FilterDto filter, String employeeName) {
        return repository.employeeWorkTimeByPeriod(filter.getStartPeriod().toLocalDate(),
                filter.getEndPeriod().toLocalDate(), employeeName);
    }

    @Override
    public long employeeDistractionTimeByDate(FilterDto filter, String employeeName) {
        return repository.employeeDistractionTimeByPeriod(filter.getStartPeriod().toLocalDate(),
                filter.getEndPeriod().toLocalDate(), employeeName);
    }

    @Override
    public long employeeRestTimeByDate(FilterDto filter, String employeeName) {
        return repository.employeeRestTimeByPeriod(filter.getStartPeriod().toLocalDate(),
                filter.getEndPeriod().toLocalDate(), employeeName);
    }

    @Override
    public long employeeLunchTimeByDate(FilterDto filter, String employeeName) {
        return repository.employeeLunchTimeByPeriod(filter.getStartPeriod().toLocalDate(),
                filter.getEndPeriod().toLocalDate(), employeeName);
    }
}
