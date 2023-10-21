package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.model.dto.DistractionDto;
import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.entity.Distraction;
import ru.egartech.tmsystem.model.entity.Employee;

import java.time.Duration;

@Mapper(componentModel = "spring")
public abstract class DistractionMapper {

    public DistractionDto toDto(Distraction distraction) {
        if (distraction == null) {
            return null;
        }

        DistractionDto distractionDto = new DistractionDto();

        distractionDto.setId(distraction.getId());
        distractionDto.setDate(distraction.getDate());
        distractionDto.setStartDistraction(distraction.getStartDistraction());
        distractionDto.setEndDistraction(distraction.getEndDistraction());
        if (distraction.getStartDistraction() == null || distraction.getEndDistraction() == null) {
            distractionDto.setDistractionTime(0L);
        } else {
            distractionDto.setDistractionTime(distraction.getDistractionTime());
        }
        distractionDto.setEmployee(employeeEntityToDto(distraction.getEmployee()));

        return distractionDto;
    }

    public Distraction toEntity(DistractionDto dto) {
        Distraction distraction = new Distraction();
        distraction.setId(dto.getId());
        distraction.setDate(dto.getDate());
        distraction.setStartDistraction(dto.getStartDistraction());
        distraction.setEndDistraction(dto.getEndDistraction());
        if (dto.getStartDistraction() == null || dto.getEndDistraction() == null) {
            distraction.setDistractionTime(0L);
        } else {
            distraction.setDistractionTime(Duration.between(dto.getStartDistraction(), dto.getEndDistraction()).toMinutes());
        }
        distraction.setEmployee(employeeDtoToEntity(dto.getEmployee()));
        return distraction;
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
