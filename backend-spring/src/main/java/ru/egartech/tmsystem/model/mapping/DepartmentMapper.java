package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.model.dto.DepartmentDto;
import ru.egartech.tmsystem.model.dto.DepartmentSummaryDto;
import ru.egartech.tmsystem.model.dto.PositionDto;
import ru.egartech.tmsystem.model.dto.SettingsDto;
import ru.egartech.tmsystem.model.entity.Department;
import ru.egartech.tmsystem.model.entity.Position;
import ru.egartech.tmsystem.utils.SummaryFormatter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class DepartmentMapper extends MapHelper {

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

    public Position positionDtoToPosition(PositionDto dto) {
        if (dto == null) {
            return null;
        }
        Position position = new Position();
        position.setId(dto.getId());
        position.setName(dto.getName());
        return position;
    }

    public List<Position> positionDtoListToPositionList(List<PositionDto> list) {
        if (list == null) {
            return null;
        }
        List<Position> list1 = new ArrayList<>(list.size());
        for (PositionDto positionDto : list) {
            list1.add(positionDtoToPosition(positionDto));
        }
        return list1;
    }

    public PositionDto positionToPositionDto(Position position) {
        PositionDto positionDto = new PositionDto();
        positionDto.setId(position.getId());
        positionDto.setName(position.getName());
        return positionDto;
    }

    public List<PositionDto> positionListToPositionDtoList(List<Position> list) {
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

    public DepartmentSummaryDto toDepartmentSummaryDto(DepartmentDto dto, SettingsDto settingsDto,
                                                       LocalDate startDate, LocalDate endDate,
                                                       Long workTime, Long distractionTime, Long restTime) {
        if (dto == null) {
            return null;
        }
        DepartmentSummaryDto departmentSummaryDto = new DepartmentSummaryDto();
        departmentSummaryDto.setId(dto.getId());
        SummaryFormatter.toSummaryDto(workTime, distractionTime, restTime,
                departmentSummaryDto, dto, startDate, endDate, settingsDto);
        return departmentSummaryDto;
    }
}



