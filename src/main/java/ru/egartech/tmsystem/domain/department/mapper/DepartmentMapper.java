package ru.egartech.tmsystem.domain.department.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import ru.egartech.tmsystem.domain.department.dto.DepartmentDto;
import ru.egartech.tmsystem.domain.department.entity.Department;
import ru.egartech.tmsystem.domain.helper.BaseMapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper extends BaseMapper<DepartmentDto, Department> {
}
