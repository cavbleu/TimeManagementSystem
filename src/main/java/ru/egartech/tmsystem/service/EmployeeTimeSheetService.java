package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.EmployeeTimeSheetDto;
import ru.egartech.tmsystem.model.dto.FilterDto;

import java.util.List;

public interface EmployeeTimeSheetService {

    List<EmployeeTimeSheetDto> employeeTimeSheets(FilterDto filter);
}
