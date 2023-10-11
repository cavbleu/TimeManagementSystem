package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.DepartmentDto;
import ru.egartech.tmsystem.model.dto.DepartmentSummaryDto;
import ru.egartech.tmsystem.model.entity.Department;
import ru.egartech.tmsystem.utils.BaseService;

import java.time.LocalDate;
import java.util.List;

public interface DepartmentService extends BaseService<DepartmentDto, Long> {

    List<DepartmentSummaryDto> departmentsSummary(LocalDate startDate, LocalDate endDate);
    List<DepartmentSummaryDto> departmentsSummary();

    long departmentWorkTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id);

    long departmentDistractionTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id);

    long departmentRestTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id);
    Department findByName(String name);

}
