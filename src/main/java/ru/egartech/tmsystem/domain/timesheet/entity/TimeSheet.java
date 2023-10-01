package ru.egartech.tmsystem.domain.timesheet.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.egartech.tmsystem.domain.rest.entity.Rest;
import ru.egartech.tmsystem.domain.employee.entity.Employee;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "time_management")
public class TimeSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private LocalDate date;
    //Причина отсутствия на работе
    @Column(name = "skip_reason")
    private String skipReason;
    @Column(name = "productive_time")
    private long productiveTime;

    @Temporal(TemporalType.TIME)
    @Column(name = "start_work")
    private LocalTime startWork;
    @Temporal(TemporalType.TIME)
    @Column(name = "end_work")
    private LocalTime endWork;
    //Отработанное время
    @Column(name = "work_time")
    private long workTime;

    @ManyToMany(mappedBy = "timeManagement", cascade = { CascadeType.ALL })
    @JsonIgnoreProperties("timeManagement")
    private List<Employee> employees = new ArrayList<>();

    @OneToMany(mappedBy = "timeSheet")
    @JsonIgnoreProperties("timeSheet")
    private List<Rest> rests = new ArrayList<>();

    @OneToMany(mappedBy = "timeSheet")
    @JsonIgnoreProperties("timeSheet")
    private List<Rest> distractions = new ArrayList<>();

    @OneToMany(mappedBy = "timeSheet")
    @JsonIgnoreProperties("timeSheet")
    private List<Rest> deviations = new ArrayList<>();

    public TimeSheet(LocalDate date, LocalTime startWork, LocalTime endWork) {
        this.date = date;
        this.startWork = startWork;
        this.endWork = endWork;
    }
}
