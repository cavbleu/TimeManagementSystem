package ru.egartech.tmsystem.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString(exclude = {"employees"})
@EqualsAndHashCode(callSuper = true)
public class PositionDto extends EntityDto {

    //Наименование отдела
    private DepartmentDto department;
    @JsonIgnore
    //Список сотрудников
    List<EmployeeDto> employees = new ArrayList<>();

    public PositionDto(String name, DepartmentDto department) {
        super(name);
        this.department = department;
    }
}


