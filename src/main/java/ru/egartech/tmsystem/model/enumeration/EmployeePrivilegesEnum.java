package ru.egartech.tmsystem.model.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.hibernate.id.IntegralDataTypeHolder;

import java.util.ArrayList;
import java.util.List;

import static ru.egartech.tmsystem.utils.EmployeePrivilegesBits.*;

@Getter
@RequiredArgsConstructor
public enum EmployeePrivilegesEnum {
    LATE_COUNT("Увеличенное количество опозданий на работу"),
    EARLY_LIVING_COUNT("Увеличенное количество ранних уходов с работы"),
    ABSENCE("Увеличенное количество опозданий на работу"),
    SKIP("Увеличенное количество пропусков работы"),
    REST_TIME("Увеличенное суммарное время перерывов"),
    LUNCH_TIME("Увеличенное суммарное время обедов"),
    DISTRACTION_TIME("Увеличенное суммарное время отвлечений");

    private final String name;
}
