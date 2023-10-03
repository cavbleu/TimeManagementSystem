package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.model.dto.PrivilegeDto;
import ru.egartech.tmsystem.model.entity.Privilege;
import ru.egartech.tmsystem.utils.BaseMapper;

@Mapper(componentModel = "spring")
public interface PrivilegeMapper extends BaseMapper<PrivilegeDto, Privilege> {
}
