package ru.egartech.tmsystem.domain.distraction.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.egartech.tmsystem.domain.timesheet.entity.TimeSheet;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "distraction")
public class Distraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private LocalDate date;

    @Temporal(TemporalType.TIME)
    @Column(name = "start_distraction")
    private LocalTime startDistraction;
    @Temporal(TemporalType.TIME)
    @Column(name = "end_distraction")
    private LocalTime endDistraction;
    @Column(name = "distraction_time")
    private long distractionTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("distractions")
    private TimeSheet timeSheet;
}
