package ru.egartech.tmsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.model.entity.Position;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto extends EntityDto {
    //Наименование отдела
    private String departmentName;
    //Сотрудники отдела
    private List<Employee> employees;
    //Должности, входящие в состав отдела
    private List<Position> positions;
}
