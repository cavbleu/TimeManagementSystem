package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.model.dto.DistractionDto;
import ru.egartech.tmsystem.model.entity.Distraction;
import ru.egartech.tmsystem.utils.BaseMapper;

@Mapper(componentModel = "spring")
public interface DistractionMapper extends BaseMapper<DistractionDto, Distraction> {
}
