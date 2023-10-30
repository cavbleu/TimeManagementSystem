package ru.egartech.tmsystem.model.mapping;

import org.springframework.stereotype.Component;
import ru.egartech.tmsystem.model.dto.*;
import ru.egartech.tmsystem.model.entity.*;
import ru.egartech.tmsystem.utils.BitsConverter;

@Component
public class MapHelper {

    public Employee employeeDtoToEntity(EmployeeDto dto) {
        if (dto == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setAge(dto.getAge());
        employee.setPrivilegesNumber(dto.getPrivilegesNumber());
        employee.setPosition(positionDtoToPosition(dto.getPosition()));
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
        employeeDto.setPosition(positionToPositionDto(entity.getPosition()));
        if (entity.getPrivilegesNumber() != null) {
            employeeDto.setPrivileges(String.join("; ", BitsConverter.getEmployeePrivileges(entity.getPrivilegesNumber())));
        }
        return employeeDto;
    }

    public Department departmentDtoToDepartment(DepartmentDto departmentDto) {
        if (departmentDto == null) {
            return null;
        }

        Department department = new Department();
        department.setId(departmentDto.getId());
        department.setName(departmentDto.getName());

        return department;
    }

    public Position positionDtoToPosition(PositionDto dto) {
        if (dto == null) {
            return null;
        }
        Position position = new Position();
        position.setId(dto.getId());
        position.setName(dto.getName());
        position.setDepartment(departmentDtoToDepartment(dto.getDepartment()));
        return position;
    }

    public PositionDto positionToPositionDto(Position position) {
        if (position == null) {
            return null;
        }
        PositionDto positionDto = new PositionDto();
        positionDto.setId(position.getId());
        positionDto.setName(position.getName());
        positionDto.setDepartment(departmentToDepartmentDto(position.getDepartment()));

        return positionDto;
    }

    protected DepartmentDto departmentToDepartmentDto(Department department) {
        if (department == null) {
            return null;
        }
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());
        return departmentDto;
    }

    protected RestDto restToRestDto(Rest rest) {
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
        return restDto;
    }

    protected DistractionDto distractionToDistractionDto(Distraction distraction) {
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
        return distractionDto;
    }

    protected TimeSheetDto timeSheetToTimeSheetDto(TimeSheet timeSheet) {
        TimeSheetDto timeSheetDto = new TimeSheetDto();
        timeSheetDto.setId(timeSheet.getId());
        timeSheetDto.setDate(timeSheet.getDate());
        timeSheetDto.setAbsenceReason(timeSheet.getAbsenceReason());
        timeSheetDto.setStartWork(timeSheet.getStartWork());
        timeSheetDto.setEndWork(timeSheet.getEndWork());
        if (timeSheet.getStartWork() == null || timeSheet.getEndWork() == null) {
            timeSheetDto.setWorkTime(0L);
        } else {
            timeSheetDto.setWorkTime(timeSheet.getWorkTime());
        }
        return timeSheetDto;
    }

}
