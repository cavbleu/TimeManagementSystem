package ru.egartech.tmsystem.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EmployeeRestDto {

    //Имя сотрудника
    private String employeeName;
    //Наименование отдела
    private String departmentName;
    //Наименование должности
    private String positionName;
    //Список перерывов, включает в себя обеды
    private List<RestDto> rests = new ArrayList<>();;
}
