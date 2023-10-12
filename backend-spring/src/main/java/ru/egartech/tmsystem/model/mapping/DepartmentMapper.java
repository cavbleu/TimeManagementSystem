package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.model.dto.DepartmentDto;
import ru.egartech.tmsystem.model.entity.Department;

@Mapper(componentModel = "spring")
public abstract class DepartmentMapper {

    public Department toEntity(DepartmentDto dto) {
        if ( dto == null ) {
            return null;
        }
        Department department = new Department();
        department.setId( dto.getId() );
        department.setName( dto.getName() );
        return department;
    }

    public DepartmentDto toDto(Department entity) {
        if ( entity == null ) {
            return null;
        }
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId( entity.getId() );
        departmentDto.setName( entity.getName() );
        return departmentDto;
    }
}
