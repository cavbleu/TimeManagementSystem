package ru.egartech.tmsystem.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
public class SettingsDto extends EntityDto{

    //Является ли текущим профилем
    private boolean isCurrentSettingsProfile;
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
    //Максимальное количество прогулов в месяц
    private long maxSkipWorkCountByMonth;
    //Максимальное суммарное время отвлечений в день
    private long maxDistractionTimeByDay;
    //Максимальное суммарное время, потраченное на обед в день
    private long maxLunchTimeByDay;
    //Максимальное суммарное время перерывов в день
    private long maxRestTimeByDay;
}
