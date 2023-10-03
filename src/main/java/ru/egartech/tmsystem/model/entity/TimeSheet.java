package ru.egartech.tmsystem.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.egartech.tmsystem.model.entity.Distraction;
import ru.egartech.tmsystem.model.entity.Rest;
import ru.egartech.tmsystem.model.entity.Employee;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "time_sheet")
public class TimeSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private LocalDate date;
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

    @ManyToMany(mappedBy = "timeSheet", cascade = { CascadeType.ALL })
    @JsonIgnoreProperties("timeSheet")
    private List<Employee> employees = new ArrayList<>();

    @OneToMany(mappedBy = "timeSheet")
    @JsonIgnoreProperties("timeSheet")
    private List<Rest> rests = new ArrayList<>();

    @OneToMany(mappedBy = "timeSheet")
    @JsonIgnoreProperties("timeSheet")
    private List<Distraction> distractions = new ArrayList<>();

    @OneToMany(mappedBy = "timeSheet")
    @JsonIgnoreProperties("timeSheet")
    private List<Rest> deviations = new ArrayList<>();

    public TimeSheet(LocalDate date, LocalTime startWork, LocalTime endWork) {
        this.date = date;
        this.startWork = startWork;
        this.endWork = endWork;
    }
}
