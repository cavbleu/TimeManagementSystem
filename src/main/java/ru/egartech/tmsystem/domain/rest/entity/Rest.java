package ru.egartech.tmsystem.domain.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.egartech.tmsystem.domain.timesheet.entity.TimeSheet;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "break")
public class Rest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private LocalDate date;

    @Temporal(TemporalType.TIME)
    @Column(name = "start_rest")
    private LocalTime startRest;
    @Temporal(TemporalType.TIME)
    @Column(name = "end_rest")
    private LocalTime endRest;
    @Column(name = "rest_time")
    private long restTime;

    @Temporal(TemporalType.TIME)
    @Column(name = "start_lunch")
    private LocalTime startLunch;
    @Temporal(TemporalType.TIME)
    @Column(name = "end_lunch")
    private LocalTime endLunch;
    @Column(name = "lunch_time")
    private long lunchTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("rests")
    private TimeSheet timeSheet;

}
