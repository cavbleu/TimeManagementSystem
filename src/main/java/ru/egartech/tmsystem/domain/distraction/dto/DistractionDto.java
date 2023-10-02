package ru.egartech.tmsystem.domain.distraction.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.egartech.tmsystem.domain.timesheet.entity.TimeSheet;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
public class DistractionDto {
    //Дата
    private LocalDate date;
    //Начало отвлечения от целевых программ
    private LocalTime startDistraction;
    //Окончание отвлечения от целевых программ
    private LocalTime endDistraction;
    //Суммарное время отвлечения от целевых программ
    private long distractionTime;
    private TimeSheet timeSheet;
}
