package ru.egartech.tmsystem.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
public class DistractionDto {

    private Long id;
    @JsonFormat(pattern = "dd-MM-yyyy")
    //Дата
    private LocalDate date;
    //Начало отвлечения от целевых программ
    private LocalTime startDistraction;
    //Окончание отвлечения от целевых программ
    private LocalTime endDistraction;
    //Суммарное время отвлечения от целевых программ
    private long distractionTime;
    //Сотрудник
    private EmployeeDto employee;

    public DistractionDto(LocalDate date, LocalTime startDistraction, LocalTime endDistraction, EmployeeDto employee) {
        this.date = date;
        this.startDistraction = startDistraction;
        this.endDistraction = endDistraction;
        this.employee = employee;
    }
}
