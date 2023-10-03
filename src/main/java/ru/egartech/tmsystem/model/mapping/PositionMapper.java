package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import ru.egartech.tmsystem.utils.BaseMapper;
import ru.egartech.tmsystem.model.dto.PositionDto;
import ru.egartech.tmsystem.model.entity.Position;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PositionMapper extends BaseMapper<PositionDto, Position> {
}