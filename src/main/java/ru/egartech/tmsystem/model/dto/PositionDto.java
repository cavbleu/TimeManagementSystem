package ru.egartech.tmsystem.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.egartech.tmsystem.model.entity.Employee;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PositionDto extends EntityDto {

    //Наименование отдела
    private String departmentName;
    //Список сотрудников
    List<Employee> employees;

    public PositionDto(Long id, String name, String departmentName) {
        super(id, name);
        this.departmentName = departmentName;
    }
}


