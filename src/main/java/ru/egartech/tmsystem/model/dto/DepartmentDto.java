package ru.egartech.tmsystem.model.dto;

import lombok.Getter;
import lombok.Setter;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.model.entity.Position;

import java.util.List;

@Getter
@Setter
public class DepartmentDto extends EntityDto{

    //Сотрудники отдела
    private List<EmployeeDto> employees;
}
