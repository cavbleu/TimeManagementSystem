package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.TimeSheetDto;
import ru.egartech.tmsystem.utils.BaseService;

import java.time.LocalDate;
import java.util.List;

public interface TimeSheetService extends BaseService<TimeSheetDto, Long> {

    List<TimeSheetDto> findByDateBetweenAndEmployee_Id(LocalDate startDate, LocalDate endDate, Long empId);
}
