package ru.egartech.tmsystem.utils;

import lombok.experimental.UtilityClass;
import ru.egartech.tmsystem.model.dto.PrivilegeDto;
import ru.egartech.tmsystem.model.enumeration.EmployeePrivilegesEnum;

import java.util.List;

@UtilityClass
public class DeviationFormatter {

    public String format(long count, double percent, String percentFrom) {
        return String
                .format("%d (%.1f %% %s) ",
                        count,
                        percent,
                        percentFrom);
    }

    public String format(long count) {
        return String.format("%d", count);
    }

    public long getIncreasedAmount(EmployeePrivilegesEnum privilege, List<PrivilegeDto> allPrivileges) {
        return allPrivileges.stream()
                .filter(p -> p.getName().equals(privilege.getName()))
                .findFirst()
                .map(PrivilegeDto::getIncreasedAmount)
                .orElse(0L);
    }
}
