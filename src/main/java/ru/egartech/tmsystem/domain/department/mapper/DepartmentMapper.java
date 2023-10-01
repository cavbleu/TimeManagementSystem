package ru.egartech.tmsystem.domain.department.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import ru.egartech.tmsystem.domain.department.dto.DepartmentDto;
import ru.egartech.tmsystem.domain.department.entity.Department;
import ru.egartech.tmsystem.domain.helper.EntityMapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface DepartmentMapper extends EntityMapper<DepartmentDto, Department> {
//    Department toDepartment (DepartmentDto departmentDto);
//    DepartmentDto toDepartmentDto (Department department);
}
