package ru.egartech.tmsystem.domain.timesheet.dto;

import lombok.Data;
import ru.egartech.tmsystem.domain.rest.entity.Rest;
import ru.egartech.tmsystem.domain.employee.entity.Employee;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class TimeSheetDto {
    //Дата
    private LocalDate date;
    //Причина отсутствия на работе
    private String skipReason;
    //Время начала рабочего дня
    private LocalTime startWork;
    //Время окончания рабочего дня
    private LocalTime endWork;
    //Суммарное рабочее время
    private long workTime;
    //Продуктивное время
    private long productiveTime;
    //Список всех сотрудников
    private List<Employee> employees;
    //Список всех перерывов
    private List<Rest> rests;
    //Список всех отвлечений от целевых программ
    private List<Rest> distractions;
    //Список всех отклонений от норм рабочего времени
    private List<Rest> deviations;
}
