package ru.egartech.tmsystem.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "rest")
public class Rest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    @Pattern(regexp = "(^0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4}$)", message = "{date.pattern}")
    private LocalDate date;

    @Temporal(TemporalType.TIME)
    @Column(name = "start_rest")
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "{time.pattern}")
    private LocalTime startRest;
    @Temporal(TemporalType.TIME)
    @Column(name = "end_rest")
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "{time.pattern}")
    private LocalTime endRest;
    @Column(name = "rest_time")
    private long restTime;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    private TimeSheet timeSheet;
}
