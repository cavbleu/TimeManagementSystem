package ru.egartech.tmsystem.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
public class SettingsDto extends EntityDto {

    //Является ли текущим профилем
    private boolean currentSettingsProfile;
    //Норма рабочего времени за день
    private long defaultWorkTime;
    //Норма начала рабочего дня
    private LocalTime defaultStartWork;
    //Максимальное количество опозданий в месяц
    private long maxLateCountByMonth;
    //Максимальное количество уходов с работы до истечения нормы рабочего времени
    private long maxEarlyLivingCountByMonth;
    //Максимальное количество отсутствий за месяц
    private long maxAbsenceCountByMonth;
    //Максимальное количество прогулов за месяц
    private long maxSkipCountByMonth;
    //Максимальное суммарное время отвлечений в день
    private long maxDistractionTimeByDay;
    //Максимальное суммарное время перерывов в день
    private long maxRestTimeByDay;

    public SettingsDto(String name, boolean currentSettingsProfile, long defaultWorkTime, LocalTime defaultStartWork,
                       long maxLateCountByMonth, long maxEarlyLivingCountByMonth, long maxAbsenceCountByMonth,
                       long maxSkipCountByMonth, long maxDistractionTimeByDay, long maxRestTimeByDay) {
        super(name);
        this.currentSettingsProfile = currentSettingsProfile;
        this.defaultWorkTime = defaultWorkTime;
        this.defaultStartWork = defaultStartWork;
        this.maxLateCountByMonth = maxLateCountByMonth;
        this.maxEarlyLivingCountByMonth = maxEarlyLivingCountByMonth;
        this.maxAbsenceCountByMonth = maxAbsenceCountByMonth;
        this.maxSkipCountByMonth = maxSkipCountByMonth;
        this.maxDistractionTimeByDay = maxDistractionTimeByDay;
        this.maxRestTimeByDay = maxRestTimeByDay;
    }
}
