package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.model.dto.PositionDto;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.model.entity.Position;
import ru.egartech.tmsystem.utils.BaseMapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class  PositionMapper {

    public Position toEntity(PositionDto dto) {
        if ( dto == null ) {
            return null;
        }

        Position position = new Position();
        position.setId( dto.getId() );
        position.setName( dto.getName() );
        position.setDepartment( dto.getDepartment() );
        return position;
    }

    public PositionDto toDto(Position entity) {
        if ( entity == null ) {
            return null;
        }

        PositionDto positionDto = new PositionDto();

        positionDto.setId( entity.getId() );
        positionDto.setName( entity.getName() );
        positionDto.setDepartment( entity.getDepartment() );
        List<Employee> list = entity.getEmployees();
        if ( list != null ) {
            positionDto.setEmployees( new ArrayList<Employee>( list ) );
        }

        return positionDto;
    }
}