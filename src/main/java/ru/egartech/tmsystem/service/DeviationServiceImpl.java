package ru.egartech.tmsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.model.dto.*;
import ru.egartech.tmsystem.model.repository.DeviationRepository;
import ru.egartech.tmsystem.utils.DeviationFormatter;
import ru.egartech.tmsystem.utils.SummaryFormatter;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.egartech.tmsystem.model.enumeration.EmployeePrivilegesEnum.*;

@Service
@RequiredArgsConstructor
public class DeviationServiceImpl implements DeviationService {

    private final EmployeeService employeeService;
    private final PrivilegeService privilegeService;
    private final DeviationRepository deviationRepository;
    private final SettingsService settingsService;

    @Override
    public DeviationDto deviationEmployeeByMonth(FilterDto filter, Long employeeId) {

        DeviationDto deviationsSummaryDto = new DeviationDto();
        EmployeeDto employeeDto = employeeService.findById(employeeId)
                .orElseThrow();
        SettingsDto settingsDto = settingsService.findByCurrentSettingsProfile();

        long lateCount = deviationRepository.employeeLateCountByMonth(settingsService.findByCurrentSettingsProfile().getDefaultStartWork(),
                employeeId, YearMonth.from(filter.getYearMonth()));
        long earlyLeavingCount = deviationRepository.earlyLeavingCountByEmployeeAndPeriod(settingsService.findByCurrentSettingsProfile().getDefaultWorkTime(),
                employeeId, YearMonth.from(filter.getYearMonth()));
        long absenceCount = deviationRepository.absenceCountByEmployeeAndPeriod(employeeId, YearMonth.from(filter.getYearMonth()));
        long skipCount = deviationRepository.skipCountByEmployeeAndPeriod(employeeId, YearMonth.from(filter.getYearMonth()));

        long maxLateCountByMonth = settingsDto.getMaxLateCountByMonth();
        long maxEarlyLivingCountByMonth = settingsDto.getMaxEarlyLivingCountByMonth();
        long maxAbsenceCountByMonth = settingsDto.getMaxAbsenceCountByMonth();
        long maxSkipWorkCountByMonth = settingsDto.getMaxSkipWorkCountByMonth();
        long maxDistractionTimeByDay = settingsDto.getMaxDistractionTimeByDay();
        long maxLunchTimeByDay = settingsDto.getMaxLunchTimeByDay();
        long maxRestTimeByDay = settingsDto.getMaxRestTimeByDay();


        List<PrivilegeDto> allPrivileges = privilegeService.findAll();
        List<String> currentPrivileges = Arrays.stream(employeeDto.getPrivileges().split("; ")).toList();

        int increasedLate = allPrivileges.stream()
                .filter(p -> p.getName().equals(LATE_COUNT.getName()))
                .findFirst()
                .map(PrivilegeDto::getIncreasedLate)
                .orElseThrow();
        int increasedEarlyLeaving = allPrivileges.stream()
                .filter(p -> p.getName().equals(EARLY_LIVING_COUNT.getName()))
                .findFirst()
                .map(PrivilegeDto::getIncreasedEarlyLeaving)
                .orElseThrow();
        int increasedAbsence = allPrivileges.stream()
                .filter(p -> p.getName().equals(ABSENCE.getName()))
                .findFirst()
                .map(PrivilegeDto::getIncreasedAbsence)
                .orElseThrow();
        int increasedSkip = allPrivileges.stream()
                .filter(p -> p.getName().equals(SKIP.getName()))
                .findFirst()
                .map(PrivilegeDto::getIncreasedSkip)
                .orElseThrow();
        long increasedRestTime = allPrivileges.stream()
                .filter(p -> p.getName().equals(REST_TIME.getName()))
                .findFirst()
                .map(PrivilegeDto::getIncreasedRestTime)
                .orElseThrow();
        long increasedLunchTime = allPrivileges.stream()
                .filter(p -> p.getName().equals(LUNCH_TIME.getName()))
                .findFirst()
                .map(PrivilegeDto::getIncreasedLunchTime)
                .orElseThrow();
        long increasedDistractionTime = allPrivileges.stream()
                .filter(p -> p.getName().equals(DISTRACTION_TIME.getName()))
                .findFirst()
                .map(PrivilegeDto::getIncreasedDistractionTime)
                .orElseThrow();

        maxAbsenceCountByMonth = currentPrivileges.contains(ABSENCE.getName()) ?
                maxAbsenceCountByMonth + increasedAbsence : maxAbsenceCountByMonth;
        maxEarlyLivingCountByMonth = currentPrivileges.contains(EARLY_LIVING_COUNT.getName()) ?
                maxEarlyLivingCountByMonth + increasedEarlyLeaving : maxEarlyLivingCountByMonth;
        maxSkipWorkCountByMonth = currentPrivileges.contains(SKIP.getName()) ?
                maxSkipWorkCountByMonth + increasedSkip : maxSkipWorkCountByMonth;
        maxLateCountByMonth = currentPrivileges.contains(LATE_COUNT.getName()) ?
                maxLateCountByMonth + increasedLate : maxLateCountByMonth;

        maxDistractionTimeByDay = currentPrivileges.contains(DISTRACTION_TIME.getName()) ?
                maxDistractionTimeByDay + increasedDistractionTime : maxDistractionTimeByDay;
        maxLunchTimeByDay = currentPrivileges.contains(LUNCH_TIME.getName()) ?
                maxLunchTimeByDay + increasedLunchTime : maxLunchTimeByDay;
        maxRestTimeByDay = currentPrivileges.contains(REST_TIME.getName()) ?
                maxRestTimeByDay + increasedRestTime : maxRestTimeByDay;

        long excessDistractionTimeCount = deviationRepository.excessDistractionTimeCountByEmployeeAndPeriod(employeeId,
                YearMonth.from(filter.getYearMonth()), maxDistractionTimeByDay);
        long excessLunchTimeCount = deviationRepository.excessLunchTimeCountByEmployeeAndPeriod(employeeId,
                YearMonth.from(filter.getYearMonth()), maxLunchTimeByDay);
        long excessRestTimeCount = deviationRepository.excessRestTimeCountByEmployeeAndPeriod(employeeId,
                YearMonth.from(filter.getYearMonth()), maxRestTimeByDay);
        long deviationCount = lateCount + earlyLeavingCount + absenceCount + skipCount + excessDistractionTimeCount +
                excessLunchTimeCount + excessRestTimeCount;

        long workingDaysByMonth = SummaryFormatter.getWorkingDays(filter.getYearMonth().withDayOfMonth(1),
                filter.getYearMonth().withDayOfMonth(filter.getYearMonth().getMonth().length(filter.getYearMonth().isLeapYear())));


        double latePercent = (double) lateCount * 100 / maxLateCountByMonth;
        double earlyLeavingPercent = (double) earlyLeavingCount * 100 / maxEarlyLivingCountByMonth;
        double absencePercent = (double) absenceCount * 100 / maxAbsenceCountByMonth;
        double skipPercent = (double) skipCount * 100 / maxSkipWorkCountByMonth;
        double excessDistractionTimePercent = (double) excessDistractionTimeCount * 100 / excessDistractionTimeCount * workingDaysByMonth;
        double excessLunchTimePercent = (double) excessLunchTimeCount * 100 / excessDistractionTimePercent * workingDaysByMonth;
        double excessRestTimePercent = (double) excessRestTimeCount * 100 / settingsService.findByCurrentSettingsProfile()
                .getMaxRestTimeByDay();

        deviationsSummaryDto.setEmployeeName(employeeDto.getName());
        deviationsSummaryDto.setDeviationCount(DeviationFormatter.format(deviationCount));
        deviationsSummaryDto.setLateCount(DeviationFormatter.format(lateCount, latePercent));
        deviationsSummaryDto.setEarlyLeavingCount(DeviationFormatter.format(earlyLeavingCount, earlyLeavingPercent));
        deviationsSummaryDto.setAbsenceCount(DeviationFormatter.format(absenceCount, absencePercent));
        deviationsSummaryDto.setSkipCount(DeviationFormatter.format(skipCount, skipPercent));
        deviationsSummaryDto.setExcessDistractionTimeCount(DeviationFormatter.format(excessDistractionTimeCount, excessDistractionTimePercent));
        deviationsSummaryDto.setExcessLunchTimeCount(DeviationFormatter.format(excessLunchTimeCount, excessLunchTimePercent));
        deviationsSummaryDto.setExcessRestTimeCount(DeviationFormatter.format(excessRestTimeCount, excessRestTimePercent));
        deviationsSummaryDto.setPrivileges(String.join(" ;", employeeDto.getPrivileges()));

        return deviationsSummaryDto;
    }

    @Override
    public List<DeviationDto> deviationAllEmployeesByMonth(FilterDto filter) {
        List<DeviationDto> deviations = new ArrayList<>();
        List<EmployeeDto> employees = employeeService.findAll();
        employees.forEach(e -> {
            deviations.add(deviationEmployeeByMonth(filter, e.getId()));
        });
        return deviations;
    }
}
