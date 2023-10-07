package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.entity.Rest;
import ru.egartech.tmsystem.utils.BaseService;
import ru.egartech.tmsystem.model.dto.RestDto;

public interface RestService extends BaseService<RestDto, Long> {

    RestDto update(Long timeSheetId, RestDto restDto);

    RestDto save(Long timeSheetId, RestDto restDto);
}
