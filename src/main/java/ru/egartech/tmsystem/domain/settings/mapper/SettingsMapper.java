package ru.egartech.tmsystem.domain.settings.mapper;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.domain.helper.BaseMapper;
import ru.egartech.tmsystem.domain.settings.dto.SettingsDto;
import ru.egartech.tmsystem.domain.settings.entitiy.Settings;

@Mapper(componentModel = "spring")
public interface SettingsMapper extends BaseMapper<SettingsDto, Settings> {
}
