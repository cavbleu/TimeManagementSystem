package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.EmployeeRestDto;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRestService {

    List<EmployeeRestDto> employeeRestsByPeriod(LocalDate startDate, LocalDate endDate);
}
