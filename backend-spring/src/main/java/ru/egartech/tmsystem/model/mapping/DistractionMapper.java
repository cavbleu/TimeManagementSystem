package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.egartech.tmsystem.model.dto.DistractionDto;
import ru.egartech.tmsystem.model.entity.Distraction;
import ru.egartech.tmsystem.utils.MapHelper;

import java.time.Duration;

@Mapper(componentModel = "spring")
public abstract class DistractionMapper {

    private MapHelper mapHelper;

    @Autowired
    public void setMapHelper(MapHelper mapHelper) {
        this.mapHelper = mapHelper;
    }

    public DistractionDto toDto(Distraction distraction) {
        if (distraction == null) {
            return null;
        }

        DistractionDto distractionDto = new DistractionDto();

        distractionDto.setId(distraction.getId());
        distractionDto.setDate(distraction.getDate());
        distractionDto.setStartDistraction(distraction.getStartDistraction());
        distractionDto.setEndDistraction(distraction.getEndDistraction());
        if (distraction.getStartDistraction() == null || distraction.getEndDistraction() == null) {
            distractionDto.setDistractionTime(0L);
        } else {
            distractionDto.setDistractionTime(distraction.getDistractionTime());
        }
        distractionDto.setEmployee(mapHelper.employeeEntityToDto(distraction.getEmployee()));

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
        distraction.setEmployee(mapHelper.employeeDtoToEntity(dto.getEmployee()));
        return distraction;
    }
}
