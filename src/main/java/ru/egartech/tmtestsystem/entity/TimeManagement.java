package ru.egartech.tmtestsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "time_management")
public class TimeManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private LocalDate date;

    @Temporal(TemporalType.TIME)
    @Column(name = "start_work")
    private LocalTime startWork;
    @Temporal(TemporalType.TIME)
    @Column(name = "end_work")
    private LocalTime endWork;
    @Column(name = "work_time")
    private long workTime;

    @Temporal(TemporalType.TIME)
    @Column(name = "start_lunch")
    private LocalTime startLunch;
    @Temporal(TemporalType.TIME)
    @Column(name = "end_lunch")
    private LocalTime endLunch;
    @Column(name = "lunch_time")
    private long lunchTime;

    @ManyToMany(mappedBy = "timeManagement", cascade = { CascadeType.ALL })
    @JsonIgnoreProperties("timeManagement")
    private List<Employee> employees;

    private List<Break> breaks;

    public long getWorkTime() {
        return Duration.between(startWork, endWork).toMinutes();
    }

    public long getLunchTime() {
        return Duration.between(startLunch, endLunch).toMinutes();
    }

    public TimeManagement(LocalDate date, LocalTime startWork, LocalTime endWork, LocalTime startLunch,
                          LocalTime endLunch, List<Employee> employees, long workTime, long lunchTime) {
        this.date = date;
        this.startWork = startWork;
        this.endWork = endWork;
        this.startLunch = startLunch;
        this.endLunch = endLunch;
        this.employees = employees;
        this.workTime = workTime;
        this.lunchTime = lunchTime;
    }
}
