package ru.egartech.tmsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.dto.EmployeeSummaryDto;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.model.mapping.EmployeeMapper;
import ru.egartech.tmsystem.model.repository.EmployeeRepository;
import ru.egartech.tmsystem.model.dto.FilterDto;
import ru.egartech.tmsystem.model.dto.SettingsDto;
import ru.egartech.tmsystem.utils.SummaryFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

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
        List<EmployeeSummaryDto> employeesSummary = new ArrayList<>();
        List<EmployeeDto> employees = findAll();
        SettingsDto settings = settingsService.findByCurrentSettingsProfile();

        for (EmployeeDto employee : employees) {

            EmployeeSummaryDto employeeSummaryDto = new EmployeeSummaryDto();
            long workTime = employeeWorkTimeByDate(filter, employee.getName());
            long distractionTime = employeeDistractionTimeByDate(filter, employee.getName());
            long restTime = employeeRestTimeByDate(filter, employee.getName());
            long lunchTime = employeeLunchTimeByDate(filter, employee.getName());
            SummaryFormatter.toSummaryDto(workTime, distractionTime, restTime, lunchTime,
                    employeeSummaryDto, employee, filter,settings);
            employeeSummaryDto.setPositionName(employee.getPosition().getName());
            employeeSummaryDto.setDepartmentName(employee.getDepartment().getName());
            employeesSummary.add(employeeSummaryDto);
        }

        return employeesSummary;
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
