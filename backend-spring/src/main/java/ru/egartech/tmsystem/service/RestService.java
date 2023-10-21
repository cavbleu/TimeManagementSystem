package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.RestDto;
import ru.egartech.tmsystem.utils.BaseService;

import java.time.LocalDate;
import java.util.List;

public interface RestService extends BaseService<RestDto, Long> {

    RestDto save(Long timeSheetId, RestDto restDto);

    List<RestDto> findByDateBetweenAndEmployee_Id(LocalDate startDate, LocalDate endDate, Long empId);

}
