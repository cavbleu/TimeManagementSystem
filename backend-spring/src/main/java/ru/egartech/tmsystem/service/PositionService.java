package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.PositionDto;
import ru.egartech.tmsystem.model.dto.PositionSummaryDto;
import ru.egartech.tmsystem.model.entity.Position;
import ru.egartech.tmsystem.utils.BaseService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PositionService extends BaseService<PositionDto, Long> {

    List<PositionSummaryDto> positionsSummaryByPeriod(LocalDateTime startDate, LocalDateTime endDate);

    long positionWorkTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id);

    long positionDistractionTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id);

    long positionRestTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id);

    PositionDto save(PositionDto positionDto, String departmentName);
    PositionDto update(PositionDto positionDto, String departmentName, Long id);

    Position findByName(String positionName);
}
