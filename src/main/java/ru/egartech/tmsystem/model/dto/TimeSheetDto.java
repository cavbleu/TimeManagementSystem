package ru.egartech.tmsystem.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.model.entity.Rest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
}
