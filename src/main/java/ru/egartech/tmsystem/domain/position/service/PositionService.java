package ru.egartech.tmsystem.domain.position.service;

import ru.egartech.tmsystem.domain.filter.dto.FilterDto;
import ru.egartech.tmsystem.domain.helper.BaseService;
import ru.egartech.tmsystem.domain.position.dto.PositionDto;
import ru.egartech.tmsystem.domain.position.dto.PositionSummaryDto;

import java.util.List;

public interface PositionService extends BaseService<PositionDto, Long> {

    List<PositionSummaryDto> positionsSummary(FilterDto filter);
}
