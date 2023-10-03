package ru.egartech.tmsystem.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DepartmentSummaryDto {
    private Long id;
    //Наименование отдела
    private String name;
    //Суммарное отработанное время
    private String workTime;
    //Суммарное продуктивное время
    private String productiveTime;
    //Суммарное время отвлечений от заданных целевых программ
    private String distractionTime;
    //Суммарное время потраченное на перерывы
    private String restTime;
    //Суммарное время потраченное на обед
    private String lunchTime;
    //Переработки - разница между отработанным временем и нормой рабочего времени.
    //Может быть как положительным так и отрицательным
    private String overTime;
}
