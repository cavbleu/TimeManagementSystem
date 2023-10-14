package ru.egartech.tmsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.egartech.tmsystem.model.entity.TimeSheet;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestDto {

    private Long id;
    //Дата
    private LocalDate date;
    //Время начала перерыва
    private LocalTime startRest;
    //Время окончания перерыва
    private LocalTime endRest;
    //Суммарное время перерыва
    private long restTime;
    //Табель рабочего времени сотрудника
    private TimeSheet timeSheet;
}
