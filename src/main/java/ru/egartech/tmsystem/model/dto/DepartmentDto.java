package ru.egartech.tmsystem.model.dto;

import lombok.Getter;
import lombok.Setter;
import ru.egartech.tmsystem.domain.employee.entity.Employee;

import java.util.List;

@Getter
@Setter
public class DepartmentDto {
    private Long id;
    //Наименование отдела
    private String name;
    //Сотрудники отдела
    private List<Employee> employees;
}
