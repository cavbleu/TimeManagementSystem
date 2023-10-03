package ru.egartech.tmsystem.domain.employee.service;

import ru.egartech.tmsystem.domain.employee.dto.EmployeeDto;
import ru.egartech.tmsystem.domain.employee.dto.EmployeeSummaryDto;
import ru.egartech.tmsystem.domain.filter.dto.FilterDto;
import ru.egartech.tmsystem.domain.helper.BaseService;

import java.util.List;

public interface EmployeeService extends BaseService<EmployeeDto, Long> {
    List<EmployeeSummaryDto> employeesSummary(FilterDto filter);
    long employeeWorkTimeByDate(FilterDto filter, String employeeName);

    long employeeDistractionTimeByDate(FilterDto filter, String employeeName);

    long employeeRestTimeByDate(FilterDto filter, String employeeName);

    long employeeLunchTimeByDate(FilterDto filter, String employeeName);
}
