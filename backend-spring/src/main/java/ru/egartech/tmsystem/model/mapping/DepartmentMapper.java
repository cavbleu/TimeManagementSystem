package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.model.dto.DepartmentDto;
import ru.egartech.tmsystem.model.dto.PositionDto;
import ru.egartech.tmsystem.model.entity.Department;
import ru.egartech.tmsystem.model.entity.Position;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class DepartmentMapper {


    public Department toEntity(DepartmentDto dto) {
        if (dto == null) {
            return null;
        }

        Department department = new Department();

        department.setId(dto.getId());
        department.setName(dto.getName());
        department.setPositions(positionDtoListToPositionList(dto.getPositions()));

        return department;
    }

    public DepartmentDto toDto(Department entity) {
        if (entity == null) {
            return null;
        }

        DepartmentDto departmentDto = new DepartmentDto();

        departmentDto.setId(entity.getId());
        departmentDto.setName(entity.getName());
        departmentDto.setPositions(positionListToPositionDtoList(entity.getPositions()));

        return departmentDto;
    }

    protected Position positionDtoToPosition(PositionDto positionDto) {
        if (positionDto == null) {
            return null;
        }

        Position position = new Position();

        position.setId(positionDto.getId());
        position.setName(positionDto.getName());

        return position;
    }

    protected List<Position> positionDtoListToPositionList(List<PositionDto> list) {
        if (list == null) {
            return null;
        }

        List<Position> list1 = new ArrayList<>(list.size());
        for (PositionDto positionDto : list) {
            list1.add(positionDtoToPosition(positionDto));
        }

        return list1;
    }

    protected PositionDto positionToPositionDto(Position position) {
        PositionDto positionDto = new PositionDto();
        positionDto.setId(position.getId());
        positionDto.setName(position.getName());

        return positionDto;
    }

    protected List<PositionDto> positionListToPositionDtoList(List<Position> list) {
        if (list == null) {
            return null;
        }

        List<PositionDto> list1 = new ArrayList<>(list.size());
        for (Position position : list) {
            if (position != null) {
                list1.add(positionToPositionDto(position));
            }

        }

        return list1;
    }
}



