package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.dto.PositionDto;
import ru.egartech.tmsystem.model.dto.PositionSummaryDto;
import ru.egartech.tmsystem.model.dto.SettingsDto;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.model.entity.Position;
import ru.egartech.tmsystem.utils.SummaryFormatter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PositionMapper extends MapHelper {

    public PositionDto toDto(Position entity) {
        if (entity == null) {
            return null;
        }
        PositionDto positionDto = new PositionDto();
        positionDto.setId(entity.getId());
        positionDto.setName(entity.getName());
        positionDto.setDepartment(departmentToDepartmentDto(entity.getDepartment()));
        positionDto.setEmployees(employeeListToEmployeeDtoList(entity.getEmployees()));
        return positionDto;
    }

    protected EmployeeDto employeeToEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setAge(employee.getAge());
        employeeDto.setPrivilegesNumber(employee.getPrivilegesNumber());
        return employeeDto;
    }

    protected List<EmployeeDto> employeeListToEmployeeDtoList(List<Employee> list) {
        if (list == null) {
            return null;
        }
        List<EmployeeDto> list1 = new ArrayList<>(list.size());
        for (Employee employee : list) {
            if (employee != null) {
                list1.add(employeeToEmployeeDto(employee));
            }
        }
        return list1;
    }

    public PositionSummaryDto toPositionSummaryDto(PositionDto dto, SettingsDto settingsDto, LocalDate startDate, LocalDate endDate,
                                                   Long workTime, Long distractionTime, Long restTime) {
        if (dto == null) {
            return null;
        }
        PositionSummaryDto positionSummaryDto = new PositionSummaryDto();
        positionSummaryDto.setId(dto.getId());
        SummaryFormatter.toSummaryDto(workTime, distractionTime, restTime,
                positionSummaryDto, dto, startDate, endDate, settingsDto);
        positionSummaryDto.setDepartmentName(dto.getDepartment().getName());
        positionSummaryDto.setPositionName(dto.getName());
        return positionSummaryDto;
    }
}