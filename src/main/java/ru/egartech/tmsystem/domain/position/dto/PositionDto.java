package ru.egartech.tmsystem.domain.position.dto;

import lombok.Getter;
import lombok.Setter;
import ru.egartech.tmsystem.domain.employee.entity.Employee;

import java.util.List;

@Getter
@Setter
public class PositionDto {
    //Название отдела
    String name;
    //Сотрудники отдела
    List<Employee> employees;
}


