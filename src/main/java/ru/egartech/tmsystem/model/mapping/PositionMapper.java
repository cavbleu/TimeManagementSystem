package ru.egartech.tmsystem.model.mapping;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.egartech.tmsystem.model.dto.DepartmentDto;
import ru.egartech.tmsystem.model.dto.PositionDto;
import ru.egartech.tmsystem.model.entity.Department;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.model.entity.Position;
import ru.egartech.tmsystem.service.DepartmentService;
import ru.egartech.tmsystem.utils.BaseMapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PositionMapper extends BaseMapper<PositionDto, Position> {
}