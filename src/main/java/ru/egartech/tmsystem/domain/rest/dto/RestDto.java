package ru.egartech.tmsystem.domain.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.egartech.tmsystem.domain.timesheet.entity.TimeSheet;

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
    //Табель рабочего времени
    private TimeSheet timeSheet;
}
