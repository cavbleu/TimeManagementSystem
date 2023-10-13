package ru.egartech.tmsystem.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeSummaryDto extends SummaryDto{

    //Имя сотрудника
    private String employeeName;
    //Наименование должности
    private String positionName;
    //Список привилегий
    private String privileges;
    //Возраст сотрудника
    private int age;

}
