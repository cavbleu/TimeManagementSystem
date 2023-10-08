package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.model.dto.DistractionDto;
import ru.egartech.tmsystem.model.dto.RestDto;
import ru.egartech.tmsystem.model.entity.Distraction;
import ru.egartech.tmsystem.model.entity.Rest;
import ru.egartech.tmsystem.utils.BaseMapper;

import java.time.Duration;

@Mapper(componentModel = "spring")
public abstract class DistractionMapper {

    public abstract DistractionDto toDto(Distraction distraction);

    public Distraction toEntity(DistractionDto dto) {
        Distraction distraction = new Distraction();
        distraction.setDate(dto.getDate());
        distraction.setStartDistraction(dto.getStartDistraction());
        distraction.setEndDistraction(dto.getEndDistraction());
        distraction.setTimeSheet(dto.getTimeSheet());
        distraction.setDistractionTime(Duration.between(dto.getStartDistraction(),
                dto.getEndDistraction()).toMinutes());

        return distraction;
    }
}
