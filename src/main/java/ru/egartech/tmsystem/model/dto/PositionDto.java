package ru.egartech.tmsystem.model.dto;

import lombok.Getter;
import lombok.Setter;
import ru.egartech.tmsystem.model.entity.Department;
import ru.egartech.tmsystem.model.entity.Employee;

import java.util.List;

@Getter
@Setter
public class PositionDto extends EntityDto {

    //Наименование отдела
    Department department;
    //Список сотрудников
    private List<Employee> employees;
}


