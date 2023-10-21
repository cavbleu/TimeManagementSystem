package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.dto.RestDto;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.model.entity.Rest;

import java.time.Duration;

@Mapper(componentModel = "spring")
public abstract class RestMapper {

    public RestDto toDto(Rest rest) {
        if (rest == null) {
            return null;
        }

        RestDto restDto = new RestDto();

        restDto.setId(rest.getId());
        restDto.setDate(rest.getDate());
        restDto.setStartRest(rest.getStartRest());
        restDto.setEndRest(rest.getEndRest());
        if (rest.getStartRest() == null || rest.getEndRest() == null) {
            restDto.setRestTime(0L);
        } else {
            restDto.setRestTime(rest.getRestTime());
        }
        restDto.setEmployee(employeeEntityToDto(rest.getEmployee()));
        return restDto;
    }

    public Rest toEntity(RestDto dto) {
        Rest rest = new Rest();
        rest.setId((dto.getId()));
        rest.setDate(dto.getDate());
        rest.setStartRest(dto.getStartRest());
        rest.setEndRest(dto.getEndRest());
        if (dto.getStartRest() == null || dto.getEndRest() == null) {
            rest.setRestTime(0L);
        } else {
            rest.setRestTime(Duration.between(dto.getStartRest(), dto.getEndRest()).toMinutes());
        }
        rest.setEmployee(employeeDtoToEntity(dto.getEmployee()));
        return rest;
    }

    public Employee employeeDtoToEntity(EmployeeDto dto) {
        if (dto == null) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setAge(dto.getAge());
        employee.setPrivilegesNumber(dto.getPrivilegesNumber());

        return employee;
    }


    public EmployeeDto employeeEntityToDto(Employee entity) {
        if (entity == null) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId(entity.getId());
        employeeDto.setName(entity.getName());
        employeeDto.setAge(entity.getAge());
        employeeDto.setPrivilegesNumber(entity.getPrivilegesNumber());

        return employeeDto;
    }
}
