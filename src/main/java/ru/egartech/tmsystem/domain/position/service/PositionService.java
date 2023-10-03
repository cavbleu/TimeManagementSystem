package ru.egartech.tmsystem.domain.position.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.egartech.tmsystem.domain.filter.dto.FilterDto;
import ru.egartech.tmsystem.domain.helper.BaseService;
import ru.egartech.tmsystem.domain.position.dto.PositionDto;
import ru.egartech.tmsystem.domain.position.dto.PositionSummaryDto;

import java.time.LocalDate;
import java.util.List;

public interface PositionService extends BaseService<PositionDto, Long> {

    List<PositionSummaryDto> positionsSummary(FilterDto filter);

    long positionWorkTimeByPeriod(FilterDto filter, String positionName);

    long positionDistractionTimeByPeriod(FilterDto filter, String positionName);

    long positionRestTimeByPeriod(FilterDto filter, String positionName);


    long positionLunchTimeByPeriod(FilterDto filter, String positionName);
}
