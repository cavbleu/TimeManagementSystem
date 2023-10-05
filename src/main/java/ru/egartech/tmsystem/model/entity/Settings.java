package ru.egartech.tmsystem.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "settings")
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column(name = "settings_profile", nullable = false)
    private String settingsProfile;
    @Column(name = "current_settings_profile", nullable = false)
    private Boolean currentSettingsProfile;
    @Column(name = "default_work_time", nullable = false)
    private Long defaultWorkTime;
    @Column(name = "default_start_work", nullable = false)
    private LocalTime defaultStartWork;
    @Column(name = "max_late_count_by_month", nullable = false)
    private Integer maxLateCountByMonth;
    @Column(name = "max_early_living_count_by_month", nullable = false)
    private Integer maxEarlyLivingCountByMonth;
    @Column(name = "max_absence_work_count_by_month", nullable = false)
    private Integer maxAbsenceWorkCountByMonth;
    @Column(name = "max_skip_work_count_by_month", nullable = false)
    private Integer maxSkipWorkCountByMonth;
    @Column(name = "max_rest_time_by_day", nullable = false)
    private Long maxRestTimeByDay;
    @Column(name = "max_distraction_time_by_day", nullable = false)
    private Long maxDistractionTimeByDay;
    @Column(name = "max_lunch_time_by_day", nullable = false)
    private Long maxLunchTimeByDay;
}
