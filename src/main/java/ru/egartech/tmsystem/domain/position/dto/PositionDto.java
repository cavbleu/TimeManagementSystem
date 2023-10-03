package ru.egartech.tmsystem.domain.position.dto;

import lombok.Getter;
import lombok.Setter;
import ru.egartech.tmsystem.domain.employee.entity.Employee;

import java.util.List;

@Getter
@Setter
public class PositionDto {
    private Long id;
    //Название отдела
    private String name;
    //Сотрудники отдела
    private List<Employee> employees;
}


