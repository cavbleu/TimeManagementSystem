package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.utils.BaseService;
import ru.egartech.tmsystem.model.dto.SettingsDto;

public interface SettingsService extends BaseService<SettingsDto, Long> {
    public SettingsDto findByCurrentSettingsProfile();
}
