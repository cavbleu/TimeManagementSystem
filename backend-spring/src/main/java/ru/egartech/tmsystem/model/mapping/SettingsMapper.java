package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.model.dto.SettingsDto;
import ru.egartech.tmsystem.model.entity.Settings;
import ru.egartech.tmsystem.utils.BaseMapper;

@Mapper(componentModel = "spring")
public interface SettingsMapper extends BaseMapper<SettingsDto, Settings> {
}
