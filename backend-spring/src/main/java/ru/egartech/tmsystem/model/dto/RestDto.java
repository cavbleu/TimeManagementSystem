package ru.egartech.tmsystem.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestDto {

    private Long id;
    //Дата
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    //Время начала перерыва
    private LocalTime startRest;
    //Время окончания перерыва
    private LocalTime endRest;
    //Суммарное время перерыва
    private long restTime;
    @JsonIgnore
    //Сотрудник
    private EmployeeDto employee;

    public RestDto(LocalDate date, LocalTime startRest, LocalTime endRest, EmployeeDto employee) {
        this.date = date;
        this.startRest = startRest;
        this.endRest = endRest;
        this.employee = employee;
    }
}
