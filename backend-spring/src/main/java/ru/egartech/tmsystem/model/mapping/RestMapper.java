package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.model.dto.RestDto;
import ru.egartech.tmsystem.model.entity.Rest;

import java.time.Duration;

@Mapper(componentModel = "spring")
public abstract class RestMapper extends MapHelper {

    public RestDto toDto(Rest rest) {
        RestDto restDto = restToRestDto(rest);
        restDto.setEmployee(employeeEntityToDto(rest.getEmployee()));
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
        rest.setEmployee(employeeDtoToEntity(dto.getEmployee()));
        return rest;
    }
}
