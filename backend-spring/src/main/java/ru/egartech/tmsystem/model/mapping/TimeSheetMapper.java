package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.dto.TimeSheetDto;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.model.entity.TimeSheet;

import java.time.Duration;

@Mapper(componentModel = "spring")
public abstract class TimeSheetMapper {

    public TimeSheetDto toDto(TimeSheet timeSheet) {
        if (timeSheet == null) {
            return null;
        }

        TimeSheetDto timeSheetDto = new TimeSheetDto();
        timeSheetDto.setId(timeSheet.getId());
        timeSheetDto.setDate(timeSheet.getDate());
        timeSheetDto.setAbsenceReason(timeSheet.getAbsenceReason());
        timeSheetDto.setStartWork(timeSheet.getStartWork());
        timeSheetDto.setEndWork(timeSheet.getEndWork());
        timeSheetDto.setEmployee(employeeEntityToDto(timeSheet.getEmployee()));

        if (timeSheet.getStartWork() == null || timeSheet.getEndWork() == null) {
            timeSheetDto.setWorkTime(0L);
        } else {
            timeSheetDto.setWorkTime(timeSheet.getWorkTime());
        }

        return timeSheetDto;
    }

    public TimeSheet toEntity(TimeSheetDto dto) {
        TimeSheet timeSheet = new TimeSheet();
        timeSheet.setId(dto.getId());
        timeSheet.setDate(dto.getDate());
        timeSheet.setAbsenceReason(dto.getAbsenceReason());
        timeSheet.setStartWork(dto.getStartWork());
        timeSheet.setEndWork(dto.getEndWork());
        timeSheet.setEmployee(employeeDtoToEntity(dto.getEmployee()));

        if (dto.getStartWork() == null || dto.getEndWork() == null) {
            timeSheet.setWorkTime(0L);
        } else {
            timeSheet.setWorkTime(Duration.between(dto.getStartWork(), dto.getEndWork()).toMinutes());
        }

        return timeSheet;
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
