package ru.egartech.tmsystem.domain.department.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DepartmentSummaryDto {
    private String name;
    //Суммарное отработанное время
    private String workTime;
    //Суммарное продуктивное время
    private String productiveTime;
    //Суммарное время отвлечений от заданных целевых программ
    private String distractionTime;
    //Время потраченное на перерывы
    private String restTime;
    //Переработки - разница между отработанным временем и нормой рабочего времени.
    //Может быть как положительным так и отрицательным
    private String overTime;
}
