package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.utils.BaseMapper;
@Mapper(componentModel = "spring")
public interface EmployeeMapper extends BaseMapper<EmployeeDto, Employee> {
}
