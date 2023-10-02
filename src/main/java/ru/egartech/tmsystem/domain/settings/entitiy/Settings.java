package ru.egartech.tmsystem.domain.settings.entitiy;

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
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "settings_profile")
    private String settingsProfile;
    @Column(name = "current_settings_profile")
    private boolean isCurrentSettingsProfile;
    @Column(name = "default_work_time")
    private long defaultWorkTime;
    @Column(name = "max_rest_count_by_day")
    private int maxRestCountByDay;
    @Column(name = "max_rest_time_by_day")
    private long maxRestTimeByDay;
    @Column(name = "max_late_count_by_month")
    private int maxLateCountByMonth;
    @Column(name = "max_early_living_count_by_month")
    private int maxEarlyLivingCountByMonth;
    @Column(name = "max_deviation_count_by_day")
    private int maxDeviationCountByDay;
    @Column(name = "max_deviation_time_by_day")
    private LocalTime maxDeviationTimeByDay;
    @Column(name = "max_skip_work_count_by_month")
    private int maxSkipWorkCountByMonth;
}
