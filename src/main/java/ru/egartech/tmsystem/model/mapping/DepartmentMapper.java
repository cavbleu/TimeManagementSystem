package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.model.dto.DepartmentDto;
import ru.egartech.tmsystem.model.entity.Department;
import ru.egartech.tmsystem.domain.helper.BaseMapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper extends BaseMapper<DepartmentDto, Department> {
}
