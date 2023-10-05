package ru.egartech.tmsystem.model.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.model.entity.Rest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
public class TimeSheetDto {

    Long id;
    //Дата
    private LocalDate date;
    //Причина отсутствия на работе
    private String absenceReason;
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
