package ru.egartech.tmsystem.domain.employee.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeSummaryDto {

    private Long id;
    //Полное имя
    private String name;
    //Возраст
    private int age;
    //Должность
    private String positionName;
    //Отдел
    private String departmentName;
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
