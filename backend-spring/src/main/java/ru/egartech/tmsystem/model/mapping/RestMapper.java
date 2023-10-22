package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.egartech.tmsystem.model.dto.RestDto;
import ru.egartech.tmsystem.model.entity.Rest;
import ru.egartech.tmsystem.utils.MapHelper;

import java.time.Duration;

@Mapper(componentModel = "spring")
public abstract class RestMapper {

    private MapHelper mapHelper;

    @Autowired
    public void setMapHelper(MapHelper mapHelper) {
        this.mapHelper = mapHelper;
    }

    public RestDto toDto(Rest rest) {
        if (rest == null) {
            return null;
        }

        RestDto restDto = new RestDto();

        restDto.setId(rest.getId());
        restDto.setDate(rest.getDate());
        restDto.setStartRest(rest.getStartRest());
        restDto.setEndRest(rest.getEndRest());
        if (rest.getStartRest() == null || rest.getEndRest() == null) {
            restDto.setRestTime(0L);
        } else {
            restDto.setRestTime(rest.getRestTime());
        }
        restDto.setEmployee(mapHelper.employeeEntityToDto(rest.getEmployee()));
        return restDto;
    }

    public Rest toEntity(RestDto dto) {
        Rest rest = new Rest();
        rest.setId((dto.getId()));
        rest.setDate(dto.getDate());
        rest.setStartRest(dto.getStartRest());
        rest.setEndRest(dto.getEndRest());
        if (dto.getStartRest() == null || dto.getEndRest() == null) {
            rest.setRestTime(0L);
        } else {
            rest.setRestTime(Duration.between(dto.getStartRest(), dto.getEndRest()).toMinutes());
        }
        rest.setEmployee(mapHelper.employeeDtoToEntity(dto.getEmployee()));
        return rest;
    }
}
