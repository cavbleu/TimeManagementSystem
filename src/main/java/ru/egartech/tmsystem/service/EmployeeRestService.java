package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.EmployeeRestDto;
import ru.egartech.tmsystem.model.dto.FilterDto;

import java.util.List;

public interface EmployeeRestService {

    List<EmployeeRestDto> employeeRestsByPeriod(FilterDto filterDto);
}
