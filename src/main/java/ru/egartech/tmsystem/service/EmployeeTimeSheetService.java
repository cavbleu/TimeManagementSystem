package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.EmployeeTimeSheetDto;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeTimeSheetService {

    List<EmployeeTimeSheetDto> employeeTimeSheetsByPeriod(LocalDate startDate, LocalDate endDate);
}
