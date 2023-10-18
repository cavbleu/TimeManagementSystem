package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.egartech.tmsystem.model.dto.DistractionDto;
import ru.egartech.tmsystem.model.entity.Distraction;

import java.time.Duration;

@Mapper(componentModel = "spring")
public abstract class DistractionMapper {

    @Autowired
    private EmployeeMapper employeeMapper;

    public DistractionDto toDto(Distraction distraction) {
        if (distraction == null) {
            return null;
        }

        DistractionDto distractionDto = new DistractionDto();

        distractionDto.setId(distraction.getId());
        distractionDto.setDate(distraction.getDate());
        distractionDto.setStartDistraction(distraction.getStartDistraction());
        distractionDto.setEndDistraction(distraction.getEndDistraction());
        distractionDto.setDistractionTime(distraction.getDistractionTime());
        distractionDto.setEmployee(employeeMapper.toDto(distraction.getEmployee()));

        return distractionDto;
    }

    public Distraction toEntity(DistractionDto dto) {
        Distraction distraction = new Distraction();
        distraction.setDate(dto.getDate());
        distraction.setStartDistraction(dto.getStartDistraction());
        distraction.setEndDistraction(dto.getEndDistraction());
        distraction.setDistractionTime(Duration.between(dto.getStartDistraction(),
                dto.getEndDistraction()).toMinutes());
        distraction.setEmployee(employeeMapper.toEntity(dto.getEmployee()));
        return distraction;
    }
}
