package ru.egartech.tmsystem.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.egartech.tmsystem.model.entity.Employee;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TimeSheetDto {

    Long id;
    //Дата
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    //Причина отсутствия на работе
    private String absenceReason;
    //Время начала рабочего дня
    private LocalTime startWork;
    //Время окончания рабочего дня
    private LocalTime endWork;
    //Суммарное рабочее время
    private long workTime;
    //Работник
    private EmployeeDto employee;

    public TimeSheetDto(LocalDate date, LocalTime startWork, LocalTime endWork, EmployeeDto employee) {
        this.date = date;
        this.startWork = startWork;
        this.endWork = endWork;
        this.employee = employee;
    }

}
