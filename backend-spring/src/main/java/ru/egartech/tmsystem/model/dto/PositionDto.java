package ru.egartech.tmsystem.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.egartech.tmsystem.model.entity.Department;
import ru.egartech.tmsystem.model.entity.Employee;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PositionDto extends EntityDto {

    //Наименование отдела
    private Department department;
    @JsonIgnore
    //Список сотрудников
    List<Employee> employees;

    public PositionDto(String name, Department department) {
        super(name);
        this.department = department;
    }
}


