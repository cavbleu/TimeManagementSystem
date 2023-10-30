package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.DeviationSummaryDto;
import ru.egartech.tmsystem.model.dto.PrivilegeDto;
import ru.egartech.tmsystem.model.dto.SettingsDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface DeviationService {

    DeviationSummaryDto deviationEmployeeByMonth(LocalDate yearMonth, Long employeeId);

    List<DeviationSummaryDto> deviationAllEmployeesByMonth(LocalDate yearMonth);

    long employeeLateCountByMonth(LocalTime defaultStartWork, Long id, LocalDate startDate, LocalDate endDate);

    long earlyLeavingCountByEmployeeAndPeriod(long defaultWorkTime, Long id, LocalDate startDate, LocalDate endDate);

    long absenceCountByEmployeeAndPeriod(Long id, LocalDate startDate, LocalDate endDate);

    long skipCountByEmployeeAndPeriod(Long id, LocalDate startDate, LocalDate endDate);

    long excessDistractionTimeCountByEmployeeAndPeriod(Long id, LocalDate startDate, LocalDate endDate, long maxDistractionTimeByDay);

    long excessRestTimeCountByEmployeeAndPeriod(Long id, LocalDate startDate, LocalDate endDate, long maxRestTimeByDay);

    double latePercent(SettingsDto settingsDto, Long lateCount, List<String> currentPrivileges, List<PrivilegeDto> allPrivileges);

    double earlyLeavingPercent(SettingsDto settingsDto, Long earlyLeavingCount, List<String> currentPrivileges, List<PrivilegeDto> allPrivileges);

    double absencePercent(SettingsDto settingsDto, Long absenceCount, List<String> currentPrivileges, List<PrivilegeDto> allPrivileges);

    double skipPercent(SettingsDto settingsDto, Long skipCount, List<String> currentPrivileges, List<PrivilegeDto> allPrivileges);

    long excessDistractionTimeCount(SettingsDto settingsDto, List<String> currentPrivileges, List<PrivilegeDto> allPrivileges,
                                    LocalDate startDate, LocalDate endDate, Long employeeId);

    double excessDistractionTimePercent(SettingsDto settingsDto, long excessDistractionTimeCount);

    long excessRestTimeCount(SettingsDto settingsDto, List<String> currentPrivileges, List<PrivilegeDto> allPrivileges,
                             LocalDate startDate, LocalDate endDate, Long employeeId);

    double excessRestTimePercent(SettingsDto settingsDto, long excessRestTimeCount);

}
