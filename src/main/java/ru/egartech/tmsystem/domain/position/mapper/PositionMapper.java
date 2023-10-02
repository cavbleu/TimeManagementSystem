package ru.egartech.tmsystem.domain.position.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import ru.egartech.tmsystem.domain.helper.BaseMapper;
import ru.egartech.tmsystem.domain.position.dto.PositionDto;
import ru.egartech.tmsystem.domain.position.entity.Position;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PositionMapper extends BaseMapper<PositionDto, Position> {
}