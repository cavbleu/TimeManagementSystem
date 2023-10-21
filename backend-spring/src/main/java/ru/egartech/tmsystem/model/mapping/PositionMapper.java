package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.model.dto.DepartmentDto;
import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.dto.PositionDto;
import ru.egartech.tmsystem.model.entity.Department;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.model.entity.Position;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PositionMapper {
    public Position toEntity(PositionDto dto) {
        if ( dto == null ) {
            return null;
        }

        Position position = new Position();

        position.setId( dto.getId() );
        position.setName( dto.getName() );
        position.setDepartment( departmentDtoToDepartment( dto.getDepartment() ) );

        return position;
    }

    public PositionDto toDto(Position entity) {
        if ( entity == null ) {
            return null;
        }

        PositionDto positionDto = new PositionDto();

        positionDto.setId( entity.getId() );
        positionDto.setName( entity.getName() );
        positionDto.setDepartment( departmentToDepartmentDto( entity.getDepartment() ) );
        positionDto.setEmployees( employeeListToEmployeeDtoList( entity.getEmployees() ) );

        return positionDto;
    }


    protected Department departmentDtoToDepartment(DepartmentDto departmentDto) {
        if ( departmentDto == null ) {
            return null;
        }

        Department department = new Department();
        department.setId( departmentDto.getId() );
        department.setName( departmentDto.getName() );

        return department;
    }


    protected DepartmentDto departmentToDepartmentDto(Department department) {
        if ( department == null ) {
            return null;
        }

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId( department.getId() );
        departmentDto.setName( department.getName() );

        return departmentDto;
    }

    protected EmployeeDto employeeToEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId( employee.getId() );
        employeeDto.setName( employee.getName() );
        employeeDto.setAge( employee.getAge() );
        employeeDto.setPrivilegesNumber( employee.getPrivilegesNumber() );
        return employeeDto;
    }

    protected List<EmployeeDto> employeeListToEmployeeDtoList(List<Employee> list) {
        if ( list == null ) {
            return null;
        }

        List<EmployeeDto> list1 = new ArrayList<>( list.size() );
        for ( Employee employee : list ) {
            if (employee != null) {
                list1.add( employeeToEmployeeDto( employee ) );
            }
        }

        return list1;
    }
}