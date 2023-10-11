package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.SettingsDto;
import ru.egartech.tmsystem.utils.BaseService;

public interface SettingsService extends BaseService<SettingsDto, Long> {
    SettingsDto findByCurrentSettingsProfile();
}
