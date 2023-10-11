package ru.egartech.tmsystem.model.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmployeePrivilegesEnum {
    LATE_COUNT("Увеличенное количество опозданий на работу"),
    EARLY_LIVING_COUNT("Увеличенное количество ранних уходов с работы"),
    ABSENCE("Увеличенное количество отсутствий на работе"),
    SKIP("Увеличенное количество прогулов работы"),
    REST_TIME("Увеличенное суммарное время перерывов"),
    LUNCH_TIME("Увеличенное суммарное время обедов"),
    DISTRACTION_TIME("Увеличенное суммарное время отвлечений");

    private final String name;
}
