package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.FilterDto;
import ru.egartech.tmsystem.utils.BaseService;
import ru.egartech.tmsystem.model.dto.PositionDto;
import ru.egartech.tmsystem.model.dto.PositionSummaryDto;

import java.util.List;

public interface PositionService extends BaseService<PositionDto, Long> {

    List<PositionSummaryDto> positionsSummary(FilterDto filter);

    long positionWorkTimeByPeriod(FilterDto filter, Long id);

    long positionDistractionTimeByPeriod(FilterDto filter, Long id);

    long positionRestTimeByPeriod(FilterDto filter, Long id);


    long positionLunchTimeByPeriod(FilterDto filter, Long id);
}
