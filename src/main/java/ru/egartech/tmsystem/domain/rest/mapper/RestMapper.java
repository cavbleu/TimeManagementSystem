package ru.egartech.tmsystem.domain.rest.mapper;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.domain.helper.BaseMapper;
import ru.egartech.tmsystem.domain.rest.dto.RestDto;
import ru.egartech.tmsystem.domain.rest.entity.Rest;
@Mapper(componentModel = "spring")
public interface RestMapper extends BaseMapper<RestDto, Rest> {
}
