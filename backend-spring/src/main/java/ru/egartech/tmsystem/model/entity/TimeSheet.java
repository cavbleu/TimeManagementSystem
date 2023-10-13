package ru.egartech.tmsystem.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Pattern(regexp = "(^0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4}$)", message = "{date.pattern}")
    private LocalDate date;
    @Column(name = "absence_reason")
    private String absenceReason;
    @Temporal(TemporalType.TIME)
    @Column(name = "start_work")
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "{time.pattern}")
    private LocalTime startWork;
    @Temporal(TemporalType.TIME)
    @Column(name = "end_work")
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "{time.pattern}")
    private LocalTime endWork;
    @Column(name = "work_time")
    private Long workTime;

    @ManyToOne
    @JsonIgnore
    private Employee employee;

    @OneToMany(mappedBy = "timeSheet", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    @OrderBy("date ASC")
    @OrderColumn(name = "id")
    private List<Rest> rests = new ArrayList<>();

    @OneToMany(mappedBy = "timeSheet", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("date ASC")
    @OrderColumn(name = "id")
    private List<Distraction> distractions = new ArrayList<>();

    public TimeSheet(LocalDate date, String absenceReason, LocalTime startWork, LocalTime endWork) {
        this.date = date;
        this.absenceReason = absenceReason;
        this.startWork = startWork;
        this.endWork = endWork;
    }
}
