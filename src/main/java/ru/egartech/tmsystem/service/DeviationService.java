package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.DeviationDto;
import ru.egartech.tmsystem.model.dto.FilterDto;

import java.time.LocalDate;
import java.util.List;

public interface DeviationService {

    DeviationDto deviationEmployeeByMonth(LocalDate yearMonth, Long employeeId);
    List<DeviationDto> deviationAllEmployeesByMonth(LocalDate yearMonth);
}
