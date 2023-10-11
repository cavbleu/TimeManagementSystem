package ru.egartech.tmsystem.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "date", nullable = false)
    @Pattern(regexp = "(^0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4}$)", message = "{date.pattern}")
    private LocalDate date;

    @Temporal(TemporalType.TIME)
    @Column(name = "start_distraction")
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "{time.pattern}")
    private LocalTime startDistraction;
    @Temporal(TemporalType.TIME)
    @Column(name = "end_distraction")
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "{time.pattern}")
    private LocalTime endDistraction;
    @Column(name = "distraction_time")
    private long distractionTime;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    private TimeSheet timeSheet;
}
