package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.DistractionDto;
import ru.egartech.tmsystem.model.dto.RestDto;
import ru.egartech.tmsystem.utils.BaseService;

public interface DistractionService extends BaseService<DistractionDto, Long> {

    DistractionDto save(Long timeSheetId, DistractionDto restDto);

    DistractionDto update(Long timeSheetId, Long restId, DistractionDto restDto);


}
