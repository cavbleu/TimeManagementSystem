package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.EditEmployeeDto;
import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.dto.EmployeeSummaryDto;
import ru.egartech.tmsystem.utils.BaseService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeService extends BaseService<EmployeeDto, Long> {
    List<EmployeeSummaryDto> employeesSummaryByPeriod(LocalDateTime startDate, LocalDateTime endDate);
    long employeeWorkTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id);

    long employeeDistractionTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id);

    long employeeRestTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id);

    EmployeeDto save (EmployeeDto employeeDto, String positionName, String departmentName);

    EmployeeDto update (EmployeeDto employeeDto);

    EditEmployeeDto getEditEmployeeDtoById(Long id);

}
