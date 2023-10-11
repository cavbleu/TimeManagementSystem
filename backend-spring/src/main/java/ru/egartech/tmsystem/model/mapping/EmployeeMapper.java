package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.utils.BitsConverter;

import java.util.Arrays;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {

    public Employee toEntity(EmployeeDto dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setAge(dto.getAge());
        employee.setPosition(dto.getPosition());
        employee.setPrivilegesNumber(BitsConverter.getEmployeePrivilegesNumber(Arrays.asList(dto.getPrivileges().split("; "))));

        return employee;
    }

    public EmployeeDto toDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setAge(employee.getAge());
        dto.setPosition(employee.getPosition());
        dto.setTimeSheets(employee.getTimeSheets());
        if (employee.getPrivilegesNumber() == null) {
            dto.setPrivileges("");
        } else {
            dto.setPrivileges(String.join("; ", BitsConverter.getEmployeePrivileges(employee.getPrivilegesNumber())));
        }

        return dto;
    }
}
