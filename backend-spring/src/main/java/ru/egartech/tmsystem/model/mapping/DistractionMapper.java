package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.model.dto.DistractionDto;
import ru.egartech.tmsystem.model.entity.Distraction;

import java.time.Duration;

@Mapper(componentModel = "spring")
public abstract class DistractionMapper extends MapHelper {

    public DistractionDto toDto(Distraction distraction) {
        DistractionDto distractionDto = distractionToDistractionDto(distraction);
        distractionDto.setEmployee(employeeEntityToDto(distraction.getEmployee()));
        return distractionDto;
    }

    public Distraction toEntity(DistractionDto dto) {
        Distraction distraction = new Distraction();
        distraction.setId(dto.getId());
        distraction.setDate(dto.getDate());
        distraction.setStartDistraction(dto.getStartDistraction());
        distraction.setEndDistraction(dto.getEndDistraction());
        if (dto.getStartDistraction() == null || dto.getEndDistraction() == null) {
            distraction.setDistractionTime(0L);
        } else {
            distraction.setDistractionTime(Duration.between(dto.getStartDistraction(), dto.getEndDistraction()).toMinutes());
        }
        distraction.setEmployee(employeeDtoToEntity(dto.getEmployee()));
        return distraction;
    }
}
