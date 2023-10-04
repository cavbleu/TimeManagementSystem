package ru.egartech.tmsystem.model.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import ru.egartech.tmsystem.model.entity.TimeSheet;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
public class DistractionDto {

    private Long id;
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
