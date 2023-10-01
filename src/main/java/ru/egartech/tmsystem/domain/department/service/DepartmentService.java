package ru.egartech.tmsystem.domain.department.service;

import ru.egartech.tmsystem.domain.department.dto.DepartmentDto;
import ru.egartech.tmsystem.domain.department.dto.DepartmentSummaryDto;
import ru.egartech.tmsystem.domain.distraction.entity.Distraction;
import ru.egartech.tmsystem.domain.filter.dto.FilterDto;
import ru.egartech.tmsystem.domain.helper.BaseService;
import ru.egartech.tmsystem.domain.limit.entitiy.Limit;
import ru.egartech.tmsystem.domain.rest.entity.Rest;
import ru.egartech.tmsystem.domain.timesheet.entity.TimeSheet;

import java.util.List;

public interface DepartmentService extends BaseService<DepartmentDto, Long> {

    List<DepartmentSummaryDto> departmentsSummary(FilterDto filter, List<TimeSheet> timeSheets,
                                                  List<Rest> rests, List<Distraction> distractions, Limit limit);
}
