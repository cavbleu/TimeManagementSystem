package ru.egartech.tmsystem.domain.limit.entitiy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "limit")
public class Limit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Норма по отработанному времени за сутки
    @Column(name = "default_work_time")
    private long defaultWorkTime;
    @Column(name = "max_break_count")
    private int maxBreakCount;
    @Column(name = "max_break_time")
    private long maxBreakTime;
    @Column(name = "max_late_count")
    private int maxLateCount;
    @Column(name = "max_early_living_count")
    private int maxEarlyLivingCount;
    @Column(name = "max_deviation_count")
    private int maxDeviationCount;
    @Column(name = "max_deviation_time")
    private LocalTime maxDeviationTime;
    @Column(name = "max_skip_work_count")
    private int maxSkipWorkCount;
}
