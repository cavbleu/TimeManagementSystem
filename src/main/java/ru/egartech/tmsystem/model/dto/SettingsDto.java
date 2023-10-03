package ru.egartech.tmsystem.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
public class SettingsDto {
    private Long id;
    //Наименование профиля настроек
    private String settingsProfile;
    //Является ли текущим профилем
    private boolean isCurrentSettingsProfile;
    //Норма рабочего времени за сутки
    private long defaultWorkTime;
    //Максимальное количество перерывов в день
    private int maxRestCountByDay;
    //Максимальное суммарное время перерывов в день
    private long maxRestTimeByDay;
    //Максимальное количество опозданий в месяц
    private int maxLateCountByMonth;
    //Максимальное количество уходов с работы до истечения нормы рабочего времени
    private int maxEarlyLivingCountByMonth;
    //Максимальное количество отвлечений в день
    private int maxDeviationCountByDay;
    //Максимальное суммарное время отвлечений в день
    private LocalTime maxDeviationTimeByDay;
    //Максимальное количество прогулов в месяц
    private int maxSkipWorkCountByMonth;
}
