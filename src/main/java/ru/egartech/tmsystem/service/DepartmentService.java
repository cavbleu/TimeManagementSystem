package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.DepartmentDto;
import ru.egartech.tmsystem.model.dto.DepartmentSummaryDto;
import ru.egartech.tmsystem.model.dto.FilterDto;
import ru.egartech.tmsystem.utils.BaseService;

import java.util.List;

public interface DepartmentService extends BaseService<DepartmentDto, Long> {

    List<DepartmentSummaryDto> departmentsSummary(FilterDto filter);

    long departmentWorkTimeByDate(FilterDto filter, Long id);

    long departmentDistractionTimeByDate(FilterDto filter, Long id);

    long departmentRestTimeByDate(FilterDto filter, Long id);

    long departmentLunchTimeByDate(FilterDto filter, Long id);

    long summaryWorkTimeByDate(FilterDto filter);

    long summaryDistractionTimeByDate(FilterDto filter);

    long summaryRestTimeByDate(FilterDto filter);

    long summaryLunchTimeByDate(FilterDto filter);

}
