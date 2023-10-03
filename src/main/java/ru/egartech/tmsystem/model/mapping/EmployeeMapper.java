package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.model.enumeration.EmployeePrivilegesEnum;
import ru.egartech.tmsystem.utils.BaseMapper;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {

    public Employee toEntity(EmployeeDto dto) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setAge(dto.getAge());
        employee.setPosition(dto.getPosition());
        employee.setDepartment(dto.getDepartment());
        employee.setTimeSheet(dto.getTimeSheet());
        employee.setPrivilegesNumber(EmployeePrivilegesEnum.getEmployeePrivilegesNumber(dto.getPrivileges()));

        return employee;
    }

    public EmployeeDto toDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setAge(employee.getAge());
        dto.setPosition(employee.getPosition());
        dto.setDepartment(employee.getDepartment());
        dto.setTimeSheet(employee.getTimeSheet());
        dto.setPrivileges(EmployeePrivilegesEnum.getEmployeePrivileges(employee.getPrivilegesNumber()));

        return dto;
    }
}
