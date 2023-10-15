package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.RestDto;
import ru.egartech.tmsystem.utils.BaseService;

public interface RestService extends BaseService<RestDto, Long> {

    RestDto update(Long restId, RestDto restDto);

    RestDto save(Long timeSheetId, RestDto restDto);
}
