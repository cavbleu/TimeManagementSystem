package ru.egartech.tmsystem.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class SummaryDto {

    Long id;
    //Наименование отдела
    private String departmentName;
    //Суммарное отработанное время
    private String workTime;
    //Суммарное продуктивное время
    private String productiveTime;
    //Суммарное время отвлечений от заданных целевых программ
    private String distractionTime;
    //Суммарное время перерывов
    private String restTime;
    //Переработки - разница между отработанным временем и нормой рабочего времени.
    //Может быть как положительным так и отрицательным
    private String overTime;
}
