package ru.egartech.tmsystem.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrivilegeDto extends EntityDto{

    //Увеличенное суммарное количество опозданий в месяц
    private int late;
    //Увеличенное суммарное количество ранних уходов с работы в месяц
    private int increasedEarlyLeaving;
    //Увеличенное суммарное количество отсутствий на работе в месяц
    private int increasedAbsence;
    //Увеличенное суммарное количество пропусков работы в месяц
    private int increasedSkip;
    //Увеличенное суммарное количество времени перерывов за день
    private long increasedRestTime;
    //Увеличенное суммарное количество времени, потраченного на обед за день
    private long increasedLunchTime;
    //Увеличенное суммарное количество времени отвлечений от целевых программ за день
    private long increasedDistractionTime;
}
