package ru.egartech.tmsystem.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(cascade = CascadeType.ALL)
    private Employee employee;


    public TimeSheet(LocalDate date, String absenceReason, LocalTime startWork, LocalTime endWork) {
        this.date = date;
        this.absenceReason = absenceReason;
        this.startWork = startWork;
        this.endWork = endWork;
    }

}
