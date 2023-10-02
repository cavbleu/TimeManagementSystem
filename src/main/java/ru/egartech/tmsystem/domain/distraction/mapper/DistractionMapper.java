package ru.egartech.tmsystem.domain.distraction.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import ru.egartech.tmsystem.domain.distraction.dto.DistractionDto;
import ru.egartech.tmsystem.domain.distraction.entity.Distraction;
import ru.egartech.tmsystem.domain.helper.BaseMapper;

@Mapper(componentModel = "spring")
public interface DistractionMapper extends BaseMapper<DistractionDto, Distraction> {
}
