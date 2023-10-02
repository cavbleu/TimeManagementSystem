package ru.egartech.tmsystem.domain.employee.mapper;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.domain.employee.dto.EmployeeDto;
import ru.egartech.tmsystem.domain.employee.entity.Employee;
import ru.egartech.tmsystem.domain.helper.BaseMapper;
@Mapper(componentModel = "spring")
public interface EmployeeMapper extends BaseMapper<EmployeeDto, Employee> {
}
