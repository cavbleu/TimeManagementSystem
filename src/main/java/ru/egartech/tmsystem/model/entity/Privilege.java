package ru.egartech.tmsystem.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "privilege")
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "increased_late", nullable = false)
    private int increasedLate;
    @Column(name = "increased_early_leaving", nullable = false)
    private int increasedEarlyLeaving;
    @Column(name = "increased_absence", nullable = false)
    private int increasedAbsence;
    @Column(name = "increased_skip", nullable = false)
    private int increasedSkip;
    @Column(name = "increased_rest_time", nullable = false)
    private long increasedRestTime;
    @Column(name = "increased_lunch_time", nullable = false)
    private long increasedLunchTime;
    @Column(name = "increased_distraction_time", nullable = false)
    private long increasedDistractionTime;
}
