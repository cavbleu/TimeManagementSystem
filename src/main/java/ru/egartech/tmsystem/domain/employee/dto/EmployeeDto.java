package ru.egartech.tmsystem.domain.employee.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.egartech.tmsystem.domain.department.entity.Department;
import ru.egartech.tmsystem.domain.position.entity.Position;
import ru.egartech.tmsystem.domain.timesheet.entity.TimeSheet;

import java.util.List;

@Setter
@Getter
public class EmployeeDto {
    //Полное имя
    private String fullName;
    //Возраст
    private int age;
    //Должность
    private Position position;
    //Отдел
    private Department department;
    //Список табелей рабочего времени
    private List<TimeSheet> timeSheet;
}
