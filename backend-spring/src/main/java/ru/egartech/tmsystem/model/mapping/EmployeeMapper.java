package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.model.dto.EditEmployeeDto;
import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.utils.BitsConverter;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {

    public Employee toEntity(EmployeeDto dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setAge(dto.getAge());
        employee.setPosition(dto.getPosition());
        employee.setPrivilegesNumber(dto.getPrivilegesNumber());
        employee.setRests(dto.getRests());
        employee.setDistractions(dto.getDistractions());
        employee.setTimeSheets(dto.getTimeSheets());
        return employee;
    }

    public Employee toEntity(EditEmployeeDto dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setAge(dto.getAge());
        employee.setPosition(dto.getPosition());
        employee.setPrivilegesNumber(BitsConverter.getEmployeePrivilegesNumber(BitsConverter.getEmployeePrivilegesList(dto)));
        return employee;
    }

    public EmployeeDto toDto(EditEmployeeDto dto) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(dto.getId());
        employeeDto.setName(dto.getName());
        employeeDto.setAge(dto.getAge());
        employeeDto.setPosition(dto.getPosition());
        employeeDto.setPrivilegesNumber(BitsConverter.getEmployeePrivilegesNumber(BitsConverter.getEmployeePrivilegesList(dto)));
        return employeeDto;
    }

    public EmployeeDto toDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setAge(employee.getAge());
        dto.setPosition(employee.getPosition());
        dto.setPrivilegesNumber(employee.getPrivilegesNumber());
        dto.setTimeSheets(employee.getTimeSheets());
        dto.setRests(employee.getRests());
        dto.setDistractions(employee.getDistractions());
        if (employee.getPrivilegesNumber() == null) {
            dto.setPrivileges("");
        } else {
            dto.setPrivileges(String.join("; ", BitsConverter.getEmployeePrivileges(employee.getPrivilegesNumber())));
        }

        return dto;
    }
}
