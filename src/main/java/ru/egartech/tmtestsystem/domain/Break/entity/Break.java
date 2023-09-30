package ru.egartech.tmtestsystem.domain.Break.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.egartech.tmtestsystem.domain.TimeSheet.entity.TimeSheet;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "break")
public class Break {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private LocalDate date;

    @Temporal(TemporalType.TIME)
    @Column(name = "start_lunch")
    private LocalTime startBreak;
    @Temporal(TemporalType.TIME)
    @Column(name = "end_lunch")
    private LocalTime endBreak;
    @Column(name = "lunch_time")
    private long breakTime;

    @Temporal(TemporalType.TIME)
    @Column(name = "start_lunch")
    private LocalTime startLunch;
    @Temporal(TemporalType.TIME)
    @Column(name = "end_lunch")
    private LocalTime endLunch;
    @Column(name = "lunch_time")
    private long lunchTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("breaks")
    private TimeSheet timeSheet;

    public long getBreakTime() {
        return Duration.between(startBreak, endBreak).toMinutes();
    }
    public long getLunchTime() {
        return Duration.between(startLunch, endLunch).toMinutes();
    }
}
