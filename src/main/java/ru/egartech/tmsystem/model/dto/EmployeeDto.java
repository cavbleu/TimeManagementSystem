package ru.egartech.tmsystem.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.egartech.tmsystem.model.entity.Department;
import ru.egartech.tmsystem.model.entity.Position;
import ru.egartech.tmsystem.model.entity.TimeSheet;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto extends EntityDto {

    //Возраст
    private int age;
    //Список привилегий
    private String privileges;
    //Должность
    private Position position;
    //Список табелей рабочего времени
    private List<TimeSheet> timeSheets;

}
