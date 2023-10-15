package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.model.dto.RestDto;
import ru.egartech.tmsystem.model.entity.Rest;

import java.time.Duration;

@Mapper(componentModel = "spring")
public abstract class RestMapper {

    public RestDto toDto(Rest rest) {
        if ( rest == null ) {
            return null;
        }

        RestDto restDto = new RestDto();

        restDto.setId( rest.getId() );
        restDto.setDate( rest.getDate() );
        restDto.setStartRest( rest.getStartRest() );
        restDto.setEndRest( rest.getEndRest() );
        restDto.setRestTime( rest.getRestTime() );
        restDto.setEmployee(rest.getEmployee());
        return restDto;
    }

    public Rest toEntity(RestDto dto) {
        Rest rest = new Rest();
        rest.setDate(dto.getDate());
        rest.setStartRest(dto.getStartRest());
        rest.setEndRest(dto.getEndRest());
        rest.setRestTime(Duration.between(dto.getStartRest(), dto.getEndRest()).toMinutes());
        rest.setEmployee(dto.getEmployee());
        return rest;
    }
}
