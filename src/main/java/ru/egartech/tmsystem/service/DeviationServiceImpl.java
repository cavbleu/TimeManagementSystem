package ru.egartech.tmsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.model.dto.*;
import ru.egartech.tmsystem.model.repository.DeviationRepository;
import ru.egartech.tmsystem.utils.DeviationFormatter;
import ru.egartech.tmsystem.utils.SummaryFormatter;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static ru.egartech.tmsystem.model.enumeration.EmployeePrivilegesEnum.*;

@Service
@RequiredArgsConstructor
public class DeviationServiceImpl implements DeviationService {

    private final EmployeeService employeeService;
    private final PrivilegeService privilegeService;
    private final SettingsService settingsService;
//    private final EmployeeRepository employeeRepository;
    private final DeviationRepository deviationRepository;

    @Override
    public DeviationDto deviationEmployeeByMonth(LocalDate yearMonth, Long employeeId) {

        DeviationDto deviationsSummaryDto = new DeviationDto();
        EmployeeDto employeeDto = employeeService.findById(employeeId)
                .orElseThrow();
        SettingsDto settingsDto = settingsService.findByCurrentSettingsProfile();

        long lateCount = deviationRepository.employeeLateCountByMonth(settingsService.findByCurrentSettingsProfile().getDefaultStartWork(),
                employeeId, YearMonth.from(yearMonth));
        long earlyLeavingCount = deviationRepository.earlyLeavingCountByEmployeeAndPeriod(settingsService.findByCurrentSettingsProfile().getDefaultWorkTime(),
                employeeId, YearMonth.from(yearMonth));
        long absenceCount = deviationRepository.absenceCountByEmployeeAndPeriod(employeeId, YearMonth.from(yearMonth));
        long skipCount = deviationRepository.skipCountByEmployeeAndPeriod(employeeId, YearMonth.from(yearMonth));

        long maxLateCountByMonth = settingsDto.getMaxLateCountByMonth();
        long maxEarlyLivingCountByMonth = settingsDto.getMaxEarlyLivingCountByMonth();
        long maxAbsenceCountByMonth = settingsDto.getMaxAbsenceCountByMonth();
        long maxSkipWorkCountByMonth = settingsDto.getMaxSkipWorkCountByMonth();
        long maxDistractionTimeByDay = settingsDto.getMaxDistractionTimeByDay();
        long maxLunchTimeByDay = settingsDto.getMaxLunchTimeByDay();
        long maxRestTimeByDay = settingsDto.getMaxRestTimeByDay();


        List<PrivilegeDto> allPrivileges = privilegeService.findAll();
        List<String> currentPrivileges = employeeDto.getPrivileges();

        long increasedLate = DeviationFormatter.getIncreasedAmount(LATE_COUNT, allPrivileges);
        long increasedEarlyLeaving = DeviationFormatter.getIncreasedAmount(EARLY_LIVING_COUNT, allPrivileges);
        long increasedAbsence = DeviationFormatter.getIncreasedAmount(ABSENCE, allPrivileges);
        long increasedSkip = DeviationFormatter.getIncreasedAmount(SKIP, allPrivileges);
        long increasedRestTime = DeviationFormatter.getIncreasedAmount(REST_TIME, allPrivileges);
        long increasedLunchTime = DeviationFormatter.getIncreasedAmount(LUNCH_TIME, allPrivileges);
        long increasedDistractionTime = DeviationFormatter.getIncreasedAmount(DISTRACTION_TIME, allPrivileges);

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
                YearMonth.from(yearMonth), maxDistractionTimeByDay);
        long excessLunchTimeCount = deviationRepository.excessLunchTimeCountByEmployeeAndPeriod(employeeId,
                YearMonth.from(yearMonth), maxLunchTimeByDay);
        long excessRestTimeCount = deviationRepository.excessRestTimeCountByEmployeeAndPeriod(employeeId,
                YearMonth.from(yearMonth), maxRestTimeByDay);
        long deviationCount = lateCount + earlyLeavingCount + absenceCount + skipCount + excessDistractionTimeCount +
                excessLunchTimeCount + excessRestTimeCount;

        long workingDaysByMonth = SummaryFormatter.getWorkingDays(yearMonth.withDayOfMonth(1),
                yearMonth.withDayOfMonth(yearMonth.getMonth().length(yearMonth.isLeapYear())));


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
    public List<DeviationDto> deviationAllEmployeesByMonth(LocalDate yearMonth) {
        List<DeviationDto> deviations = new ArrayList<>();
        List<EmployeeDto> employees = employeeService.findAll();
        employees.forEach(e -> {
            deviations.add(deviationEmployeeByMonth(yearMonth, e.getId()));
        });
        return deviations;
    }
}
