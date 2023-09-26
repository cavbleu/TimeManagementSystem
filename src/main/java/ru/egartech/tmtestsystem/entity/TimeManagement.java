package ru.egartech.tmtestsystem.entity;

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
    @Column(name = "time_management_id")
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
    @Temporal(TemporalType.TIME)
    @Column(name = "start_break")
    private LocalTime startBreak;
    @Temporal(TemporalType.TIME)
    @Column(name = "end_break")
    private LocalTime endBreak;
    @Temporal(TemporalType.TIME)
    @Column(name = "start_lunch")
    private LocalTime startLunch;
    @Temporal(TemporalType.TIME)
    @Column(name = "end_lunch")
    private LocalTime endLunch;
    @ManyToMany(mappedBy = "timeManagement", cascade = { CascadeType.ALL })
    private List<Employee> employees;
    @Column(name = "work_time")
    private long workTime;
    @Column(name = "lunch_time")
    private long lunchTime;
    @Column(name = "break_time")
    private long breakTime;

    public long getWorkTime() {
        return Duration.between(startWork, endWork).toMinutes();
    }

    public long getLunchTime() {
        return Duration.between(startLunch, endLunch).toMinutes();
    }

    public long getBreakTime() {
        return Duration.between(startBreak, endBreak).toMinutes();
    }

    public TimeManagement(LocalDateTime startWork, LocalDateTime endWork, LocalDateTime startBreak,
                          LocalDateTime endBreak, LocalDateTime startLunch, LocalDateTime endLunch,
                          List<Employee> employees, long workTime, long lunchTime, long breakTime) {
        this.startWork = startWork;
        this.endWork = endWork;
        this.startBreak = startBreak;
        this.endBreak = endBreak;
        this.startLunch = startLunch;
        this.endLunch = endLunch;
        this.employees = employees;
        this.workTime = workTime;
        this.lunchTime = lunchTime;
        this.breakTime = breakTime;
    }
}
