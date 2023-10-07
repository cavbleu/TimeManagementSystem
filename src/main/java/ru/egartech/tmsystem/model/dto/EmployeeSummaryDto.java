package ru.egartech.tmsystem.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeSummaryDto extends SummaryDto{

    //Имя сотрудника
    private String name;
    //Наименование должности
    private String positionName;
    //Список привилегий
    private String privileges;

}
