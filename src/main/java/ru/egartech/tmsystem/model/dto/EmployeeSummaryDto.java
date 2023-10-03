package ru.egartech.tmsystem.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeSummaryDto extends SummaryDto{
    //Наименование должности
    private String positionName;
    //Наименование отдела
    private String departmentName;
    //Список привилегий
    private String privileges;

}
