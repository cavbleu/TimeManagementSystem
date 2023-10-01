package ru.egartech.tmsystem.domain.timesheet.dto;

import lombok.Data;
import ru.egartech.tmsystem.domain.rest.entity.Rest;
import ru.egartech.tmsystem.domain.employee.entity.Employee;

import java.time.LocalDate;
import java.util.List;

@Data
public class TimeSheetDTO {
    private Long id;
    private LocalDate date;
    private String skipReason;
    private String startWork;
    private String endWork;
    private String workTime;
    private List<Employee> employees;
    private List<Rest> rests;
    private List<Rest> distractions;
    private List<Rest> deviations;
}
