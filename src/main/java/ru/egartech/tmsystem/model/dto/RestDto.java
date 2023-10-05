package ru.egartech.tmsystem.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import ru.egartech.tmsystem.model.entity.TimeSheet;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
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
    //Время начала обеда
    private LocalTime startLunch;
    //Время окончания обеда
    private LocalTime endLunch;
    //Суммарное время обеда
    private long lunchTime;
//    //Табель времени сотрудника
//    private TimeSheet timeSheet;
}
