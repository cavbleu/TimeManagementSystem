package ru.egartech.tmsystem.model.mapping;

import org.hibernate.annotations.Comment;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.utils.BitsConverter;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {

    public Employee toEntity(EmployeeDto dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setAge(dto.getAge());
        employee.setPrivilegesNumber(BitsConverter.getEmployeePrivilegesNumber(dto.getPrivileges()));

        return employee;
    }

    public EmployeeDto toDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setAge(employee.getAge());
        dto.setPosition(employee.getPosition());
        dto.setDepartment(employee.getDepartment());
        if(employee.getPrivilegesNumber() == null){
            dto.setPrivileges(BitsConverter.getEmployeePrivileges(0));
        } else {
            dto.setPrivileges(BitsConverter.getEmployeePrivileges(employee.getPrivilegesNumber()));
        }

        return dto;
    }
}
