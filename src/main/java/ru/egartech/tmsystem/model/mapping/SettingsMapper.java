package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.utils.BaseMapper;
import ru.egartech.tmsystem.model.dto.SettingsDto;
import ru.egartech.tmsystem.model.entity.Settings;

@Mapper(componentModel = "spring")
public interface SettingsMapper extends BaseMapper<SettingsDto, Settings> {
}
