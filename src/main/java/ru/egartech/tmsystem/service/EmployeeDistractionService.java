package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.EmployeeDistractionDto;
import ru.egartech.tmsystem.model.dto.FilterDto;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeDistractionService {

    List<EmployeeDistractionDto> employeeDistractionsByPeriod(LocalDate startDate, LocalDate endDate);

}
