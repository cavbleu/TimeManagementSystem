package ru.egartech.tmsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SettingsDto extends EntityDto{

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
    //Максимальное количество прогулов в месяц
    private long maxSkipCountByMonth;
    //Максимальное суммарное время отвлечений в день
    private long maxDistractionTimeByDay;
    //Максимальное суммарное время перерывов в день
    private long maxRestTimeByDay;
}
