package ru.egartech.tmtestsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public long getBreakTime() {
        return Duration.between(startBreak, endBreak).toMinutes();
    }
}
