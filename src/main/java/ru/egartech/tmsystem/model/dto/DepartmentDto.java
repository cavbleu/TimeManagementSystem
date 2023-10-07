package ru.egartech.tmsystem.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    //Сотрудники отдела
    private List<Employee> employees;
    @JsonIgnore
    //Должности, входящие в состав отдела
    private List<Position> positions;
}
