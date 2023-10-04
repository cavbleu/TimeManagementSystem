package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.dto.EmployeeSummaryDto;
import ru.egartech.tmsystem.model.dto.FilterDto;
import ru.egartech.tmsystem.utils.BaseService;

import java.util.List;

public interface EmployeeService extends BaseService<EmployeeDto, Long> {
    List<EmployeeSummaryDto> employeesSummary(FilterDto filter);
    long employeeWorkTimeByDate(FilterDto filter, Long id);

    long employeeDistractionTimeByDate(FilterDto filter, Long id);

    long employeeRestTimeByDate(FilterDto filter, Long id);

    long employeeLunchTimeByDate(FilterDto filter, Long id);
}
