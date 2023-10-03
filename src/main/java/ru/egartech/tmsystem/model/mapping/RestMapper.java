package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.utils.BaseMapper;
import ru.egartech.tmsystem.model.dto.RestDto;
import ru.egartech.tmsystem.model.entity.Rest;
@Mapper(componentModel = "spring")
public interface RestMapper extends BaseMapper<RestDto, Rest> {
}
