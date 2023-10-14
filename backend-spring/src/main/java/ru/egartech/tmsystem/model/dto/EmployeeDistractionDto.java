package ru.egartech.tmsystem.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EmployeeDistractionDto {

    //Имя сотрудника
    String employeeName;
    //Возраст сотрудника
    int age;
    //Наименование отдела
    String departmentName;
    //Наименование должности
    String positionName;
    //Список отвлечений
    List<DistractionDto> distractions = new ArrayList<>();;

}
