package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.DepartmentDto;
import ru.egartech.tmsystem.model.dto.DepartmentSummaryDto;
import ru.egartech.tmsystem.domain.filter.dto.FilterDto;
import ru.egartech.tmsystem.domain.helper.BaseService;

import java.util.List;

public interface DepartmentService extends BaseService<DepartmentDto, Long> {

    List<DepartmentSummaryDto> departmentsSummary(FilterDto filter);

    long departmentWorkTimeByDate(FilterDto filter, String departmentName);

    long departmentDistractionTimeByDate(FilterDto filter, String departmentName);

    long departmentRestTimeByDate(FilterDto filter, String departmentName);

    long departmentLunchTimeByDate(FilterDto filter, String departmentName);

    long summaryWorkTimeByDate(FilterDto filter);

    long summaryDistractionTimeByDate(FilterDto filter);

    long summaryRestTimeByDate(FilterDto filter);

    long summaryLunchTimeByDate(FilterDto filter);

}
