package ru.egartech.tmsystem.domain.department.service;

import ru.egartech.tmsystem.domain.department.dto.DepartmentDto;
import ru.egartech.tmsystem.domain.department.dto.DepartmentSummaryDto;
import ru.egartech.tmsystem.domain.filter.dto.FilterDto;
import ru.egartech.tmsystem.domain.helper.BaseService;

import java.util.List;

public interface DepartmentService extends BaseService<DepartmentDto, Long> {

    List<DepartmentSummaryDto> departmentsSummary(FilterDto filter);
}
