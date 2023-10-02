package ru.egartech.tmsystem.domain.settings.service;

import ru.egartech.tmsystem.domain.helper.BaseService;
import ru.egartech.tmsystem.domain.settings.dto.SettingsDto;

public interface SettingsService extends BaseService<SettingsDto, Long> {
    public SettingsDto findByCurrentSettingsProfile();
}
