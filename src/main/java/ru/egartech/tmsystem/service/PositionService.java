package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.FilterDto;
import ru.egartech.tmsystem.utils.BaseService;
import ru.egartech.tmsystem.model.dto.PositionDto;
import ru.egartech.tmsystem.model.dto.PositionSummaryDto;

import java.time.LocalDate;
import java.util.List;

public interface PositionService extends BaseService<PositionDto, Long> {

    List<PositionSummaryDto> positionsSummaryByPeriod(LocalDate startDate, LocalDate endDate);

    long positionWorkTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id);

    long positionDistractionTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id);

    long positionRestTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id);


    long positionLunchTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id);
}
