package ru.egartech.tmsystem.utils;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import ru.egartech.tmsystem.model.dto.PrivilegeDto;
import ru.egartech.tmsystem.model.enumeration.EmployeePrivilegesEnum;
import ru.egartech.tmsystem.service.PrivilegeService;

import java.time.Duration;
import java.util.List;

import static ru.egartech.tmsystem.model.enumeration.EmployeePrivilegesEnum.EARLY_LIVING_COUNT;

@UtilityClass
public class DeviationFormatter {

    public String format(long count, double percent) {
        return String
                .format("%d (%.1f) ",
                        count,
                        percent)
                .concat("%)");
    }

    public String format(long count) {
        return String.format("%d", count);
    }

    public long getIncreasedAmount(EmployeePrivilegesEnum privilege, List<PrivilegeDto> allPrivileges) {
        return allPrivileges.stream()
                .filter(p -> p.getName().equals(privilege.getName()))
                .findFirst()
                .map(PrivilegeDto::getIncreasedAmount)
                .orElseThrow();
    }
}
