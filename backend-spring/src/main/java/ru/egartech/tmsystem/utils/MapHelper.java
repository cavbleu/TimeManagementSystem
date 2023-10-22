package ru.egartech.tmsystem.utils;

import org.springframework.stereotype.Component;
import ru.egartech.tmsystem.model.dto.DepartmentDto;
import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.dto.PositionDto;
import ru.egartech.tmsystem.model.entity.Department;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.model.entity.Position;

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

    protected Department departmentDtoToDepartment(DepartmentDto departmentDto) {
        if (departmentDto == null) {
            return null;
        }

        Department department = new Department();
        department.setId(departmentDto.getId());
        department.setName(departmentDto.getName());

        return department;
    }

    protected Position positionDtoToPosition(PositionDto positionDto) {
        if (positionDto == null) {
            return null;
        }

        Position position = new Position();
        position.setId(positionDto.getId());
        position.setName(positionDto.getName());
        position.setDepartment(departmentDtoToDepartment(positionDto.getDepartment()));

        return position;
    }

    protected PositionDto positionToPositionDto(Position position) {
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

}
