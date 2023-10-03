package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.dto.EmployeeSummaryDto;
import ru.egartech.tmsystem.model.dto.FilterDto;
import ru.egartech.tmsystem.utils.BaseService;

import java.util.List;

public interface EmployeeService extends BaseService<EmployeeDto, Long> {
    List<EmployeeSummaryDto> employeesSummary(FilterDto filter);
    long employeeWorkTimeByDate(FilterDto filter, String employeeName);

    long employeeDistractionTimeByDate(FilterDto filter, String employeeName);

    long employeeRestTimeByDate(FilterDto filter, String employeeName);

    long employeeLunchTimeByDate(FilterDto filter, String employeeName);
}
