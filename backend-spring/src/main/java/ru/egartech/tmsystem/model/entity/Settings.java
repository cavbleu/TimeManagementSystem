package ru.egartech.tmsystem.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
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
    @TableGenerator( name = "Address_Gen", initialValue = 3)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Address_Gen")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @Pattern(regexp = "^[Я-аА-яa-zA-Z0-9 ]*$", message = "{name.only.letters.digits}")
    @Pattern(regexp = "^\\S+(?: \\S+)*$", message = "{name.start.end.no.spaces}")
    @Size(min = 1, message = "Наименование должно быть не менее 1 символа")
    @Size(max = 30, message = "Наименование должно быть не более 30 символов")
    private String name;

    @Column(name = "current_settings_profile", nullable = false)
    private Boolean currentSettingsProfile;

    @Column(name = "default_work_time", nullable = false)
    @PositiveOrZero(message = "{number.only.positive}")
    private Long defaultWorkTime;

    @Column(name = "default_start_work", nullable = false)
    private LocalTime defaultStartWork;

    @Column(name = "max_late_count_by_month", nullable = false)
    @PositiveOrZero(message = "{number.only.positive}")
    private Integer maxLateCountByMonth;

    @Column(name = "max_early_living_count_by_month", nullable = false)
    @PositiveOrZero(message = "{number.only.positive}")
    private Integer maxEarlyLivingCountByMonth;

    @Column(name = "max_absence_count_by_month", nullable = false)
    @PositiveOrZero(message = "{number.only.positive}")
    private Integer maxAbsenceCountByMonth;

    @Column(name = "max_skip_count_by_month", nullable = false)
    @PositiveOrZero(message = "{number.only.positive}")
    private Integer maxSkipCountByMonth;

    @Column(name = "max_rest_time_by_day", nullable = false)
    @PositiveOrZero(message = "{number.only.positive}")
    private Long maxRestTimeByDay;

    @Column(name = "max_distraction_time_by_day", nullable = false)
    @PositiveOrZero(message = "{number.only.positive}")
    private Long maxDistractionTimeByDay;

    @Column(name = "max_excess_distraction_time_by_month", nullable = false)
    @PositiveOrZero(message = "{number.only.positive}")
    private Long maxExcessDistractionCountByMonth;

    @Column(name = "max_excess_rest_time_by_month", nullable = false)
    @PositiveOrZero(message = "{number.only.positive}")
    private Long maxExcessRestCountByMonth;

}
