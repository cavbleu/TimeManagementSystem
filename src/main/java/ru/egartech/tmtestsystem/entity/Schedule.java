package ru.egartech.tmtestsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(name = "work_day")
    private LocalDate workDay;
    @Temporal(TemporalType.DATE)
    @Column(name = "weekend")
    private LocalDate weekend;
    @Temporal(TemporalType.DATE)
    @Column(name = "vacation")
    private LocalDate vacation;
    @Temporal(TemporalType.DATE)
    @Column(name = "illness")
    private LocalDate illness;
    @Temporal(TemporalType.DATE)
    @Column(name = "absence")
    private LocalDate absence;
    @ManyToMany(mappedBy = "schedule", cascade = { CascadeType.ALL })
    private List<Employee> employees;

    public Schedule(LocalDate workDay, LocalDate weekend, LocalDate vacation,
                    LocalDate illness, LocalDate absence, List<Employee> employees) {
        this.workDay = workDay;
        this.weekend = weekend;
        this.vacation = vacation;
        this.illness = illness;
        this.absence = absence;
        this.employees = employees;
    }
}
