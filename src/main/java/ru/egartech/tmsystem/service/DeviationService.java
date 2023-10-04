package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.DeviationDto;
import ru.egartech.tmsystem.model.dto.FilterDto;

import java.util.List;

public interface DeviationService {

    DeviationDto deviationEmployeeByMonth(FilterDto filter, Long employeeId);
    List<DeviationDto> deviationAllEmployeesByMonth(FilterDto filter);
}
