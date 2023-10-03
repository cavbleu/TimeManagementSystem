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

    public static List<String> getEmployeePrivileges(long privilegesBit) {
        List<String> employeePrivileges = new ArrayList<>();
        if ((privilegesBit & BIT_LATE_COUNT) != 0) {
            employeePrivileges.add(LATE_COUNT.getName());
        } else if ((privilegesBit & BIT_EARLY_LIVING_COUNT) != 0) {
            employeePrivileges.add(EARLY_LIVING_COUNT.getName());
        } else if ((privilegesBit & BIT_ABSENCE) != 0) {
            employeePrivileges.add(ABSENCE.getName());
        } else if ((privilegesBit & BIT_SKIP) != 0) {
            employeePrivileges.add(SKIP.getName());
        } else if ((privilegesBit & BIT_REST_TIME) != 0) {
            employeePrivileges.add(REST_TIME.getName());
        } else if ((privilegesBit & BIT_LUNCH_TIME) != 0) {
            employeePrivileges.add(LUNCH_TIME.getName());
        } else if ((privilegesBit & BIT_DISTRACTION_TIME) != 0) {
            employeePrivileges.add(DISTRACTION_TIME.getName());
        }

        return employeePrivileges;
    }

    public static long getEmployeePrivilegesNumber(List<String> employeePrivileges) {
        if (employeePrivileges.isEmpty()) {
            return 0;
        }
        StringBuilder privilegesNumber = new StringBuilder();
        for (EmployeePrivilegesEnum value : EmployeePrivilegesEnum.values()) {
            boolean isPresent = employeePrivileges.contains(value.getName());
            privilegesNumber.insert(0, BooleanUtils.toInteger(isPresent));
        }
        return Integer.parseInt(privilegesNumber.toString(), Character.MIN_RADIX);
    }
}
