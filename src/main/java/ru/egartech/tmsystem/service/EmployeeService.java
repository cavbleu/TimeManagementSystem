package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.dto.EmployeeSummaryDto;
import ru.egartech.tmsystem.model.dto.FilterDto;
import ru.egartech.tmsystem.utils.BaseService;

import java.util.List;

public interface EmployeeService extends BaseService<EmployeeDto, Long> {
    List<EmployeeSummaryDto> employeesSummary(FilterDto filter);
    long employeeWorkTimeByPeriod(FilterDto filter, Long id);

    long employeeDistractionTimeByPeriod(FilterDto filter, Long id);

    long employeeRestTimeByPeriod(FilterDto filter, Long id);

    long employeeLunchTimeByPeriod(FilterDto filter, Long id);
}
