package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.DistractionDto;
import ru.egartech.tmsystem.utils.BaseService;

import java.time.LocalDate;
import java.util.List;

public interface DistractionService extends BaseService<DistractionDto, Long> {

    DistractionDto save(Long timeSheetId, DistractionDto restDto);

    List<DistractionDto> findByDateBetweenAndEmployee_Id(LocalDate startDate, LocalDate endDate, Long empId);
}
