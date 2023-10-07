package ru.egartech.tmsystem.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AccessLevel;
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
    @Column(name = "absence_reason")
    private String absenceReason;
    @Temporal(TemporalType.TIME)
    @Column(name = "start_work")
    private LocalTime startWork;
    @Temporal(TemporalType.TIME)
    @Column(name = "end_work")
    private LocalTime endWork;
    @Column(name = "work_time")
    private Long workTime;

    @ManyToMany(mappedBy = "timeSheet", cascade = { CascadeType.ALL })
    @JsonIgnoreProperties("timeSheet")
    @Setter(AccessLevel.NONE)
    private List<Employee> employees = new ArrayList<>();

    @OneToMany(mappedBy = "timeSheet")
    @JsonIgnoreProperties("timeSheet")
    @Setter(AccessLevel.NONE)
    private List<Rest> rests = new ArrayList<>();

    @OneToMany(mappedBy = "timeSheet")
    @JsonIgnoreProperties("timeSheet")
    @Setter(AccessLevel.NONE)
    private List<Distraction> distractions = new ArrayList<>();

    public TimeSheet(LocalDate date, String absenceReason, LocalTime startWork, LocalTime endWork) {
        this.date = date;
        this.absenceReason = absenceReason;
        this.startWork = startWork;
        this.endWork = endWork;
    }
}
