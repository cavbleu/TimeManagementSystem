package ru.egartech.tmsystem.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployeeRestDto {

    //Имя сотрудника
    String employeeName;
    //Наименование отдела
    String departmentName;
    //Наименование должности
    String positionName;
    //Список перерывов
    List<RestDto> rests;
}
