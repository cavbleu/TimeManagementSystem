package ru.egartech.tmsystem.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "time_sheet")
@EqualsAndHashCode
public class TimeSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    @DateTimeFormat(pattern="dd-MMM-yyyy")
    @JsonFormat(pattern="dd-MM-yyyy")
    @NotNull
    private LocalDate date;

    @Column(name = "absence_reason")
    private String absenceReason;

    @Temporal(TemporalType.TIME)
    @Column(name = "start_work")
    @DateTimeFormat(pattern="HH:mm")
    private LocalTime startWork;

    @Temporal(TemporalType.TIME)
    @Column(name = "end_work")
    @DateTimeFormat(pattern="HH:mm")
    private LocalTime endWork;

    @Column(name = "work_time")
    private Long workTime;

    @ManyToOne
    @JsonIgnore
    private Employee employee;


    public TimeSheet(LocalDate date, String absenceReason, LocalTime startWork, LocalTime endWork) {
        this.date = date;
        this.absenceReason = absenceReason;
        this.startWork = startWork;
        this.endWork = endWork;
    }

    public TimeSheet(LocalDate date, LocalTime startWork, LocalTime endWork, Employee employee) {
        this.date = date;
        this.startWork = startWork;
        this.endWork = endWork;
        this.employee = employee;
    }

    public TimeSheet(Long workTime) {
        this.workTime = workTime;
    }
}
