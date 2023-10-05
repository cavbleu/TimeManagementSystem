package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.DepartmentDto;
import ru.egartech.tmsystem.model.dto.DepartmentSummaryDto;
import ru.egartech.tmsystem.model.dto.FilterDto;
import ru.egartech.tmsystem.utils.BaseService;

import java.time.LocalDate;
import java.util.List;

public interface DepartmentService extends BaseService<DepartmentDto, Long> {

    List<DepartmentSummaryDto> departmentsSummary(LocalDate startDate, LocalDate endDate);

    long departmentWorkTimeByDate(LocalDate startDate, LocalDate endDate, Long id);

    long departmentDistractionTimeByDate(LocalDate startDate, LocalDate endDate, Long id);

    long departmentRestTimeByDate(LocalDate startDate, LocalDate endDate, Long id);

    long departmentLunchTimeByDate(LocalDate startDate, LocalDate endDate, Long id);

    long summaryWorkTimeByDate(LocalDate startDate, LocalDate endDate);

    long summaryDistractionTimeByDate(LocalDate startDate, LocalDate endDate);

    long summaryRestTimeByDate(LocalDate startDate, LocalDate endDate);

    long summaryLunchTimeByDate(LocalDate startDate, LocalDate endDate);

}
