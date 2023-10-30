package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.DepartmentDto;
import ru.egartech.tmsystem.model.dto.DepartmentSummaryDto;
import ru.egartech.tmsystem.utils.BaseService;

import java.time.LocalDate;
import java.util.List;

public interface DepartmentService extends BaseService<DepartmentDto, Long> {

    List<DepartmentSummaryDto> departmentSummaryByPeriod(LocalDate startDate, LocalDate endDate);

    long departmentWorkTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id);

    long departmentDistractionTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id);

    long departmentRestTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id);

}
