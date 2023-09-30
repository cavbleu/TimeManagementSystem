package ru.egartech.tmtestsystem.domain.TimeSheet.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.egartech.tmtestsystem.domain.Break.entity.Break;
import ru.egartech.tmtestsystem.domain.Employee.entity.Employee;

import java.time.Duration;
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

    @Temporal(TemporalType.TIME)
    @Column(name = "start_work")
    private LocalTime startWork;
    @Temporal(TemporalType.TIME)
    @Column(name = "end_work")
    private LocalTime endWork;
    @Column(name = "work_time")
    private long workTime;

    @ManyToMany(mappedBy = "timeManagement", cascade = { CascadeType.ALL })
    @JsonIgnoreProperties("timeManagement")
    private List<Employee> employees = new ArrayList<>();

    @OneToMany(mappedBy = "timeSheet")
    @JsonIgnoreProperties("timeSheet")
    private List<Break> breaks = new ArrayList<>();

    public long getWorkTime() {
        return Duration.between(startWork, endWork).toMinutes();
    }

    public TimeSheet(LocalDate date, LocalTime startWork, LocalTime endWork, long workTime) {
        this.date = date;
        this.startWork = startWork;
        this.endWork = endWork;
        this.workTime = workTime;
    }
}
