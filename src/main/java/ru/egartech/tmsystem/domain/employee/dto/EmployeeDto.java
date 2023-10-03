package ru.egartech.tmsystem.domain.employee.dto;

import lombok.Getter;
import lombok.Setter;
import ru.egartech.tmsystem.model.entity.Department;
import ru.egartech.tmsystem.domain.position.entity.Position;
import ru.egartech.tmsystem.domain.timesheet.entity.TimeSheet;

import java.util.List;

@Setter
@Getter
public class EmployeeDto {
    private Long id;
    //Полное имя
    private String name;
    //Возраст
    private int age;
    //Должность
    private Position position;
    //Отдел
    private Department department;
    //Список табелей рабочего времени
    private List<TimeSheet> timeSheet;
}
