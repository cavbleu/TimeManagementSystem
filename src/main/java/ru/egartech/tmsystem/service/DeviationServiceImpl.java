package ru.egartech.tmsystem.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.exception.EmployeeNotFoundException;
import ru.egartech.tmsystem.model.dto.*;
import ru.egartech.tmsystem.model.repository.DeviationRepository;
import ru.egartech.tmsystem.utils.DeviationFormatter;
import ru.egartech.tmsystem.utils.SummaryFormatter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static ru.egartech.tmsystem.model.enumeration.EmployeePrivilegesEnum.*;

@Service
@RequiredArgsConstructor
public class DeviationServiceImpl implements DeviationService {

    private final EmployeeService employeeService;
    private final PrivilegeService privilegeService;
    private final SettingsService settingsService;
    private final DeviationRepository deviationRepository;

    private final String DEFAULT_MAX = "от максимально допустимого значения в месяц";

    @Override
    public DeviationDto deviationEmployeeByMonth(LocalDate yearMonth, Long employeeId) {

        DeviationDto deviationsSummaryDto = new DeviationDto();
        EmployeeDto employeeDto = employeeService.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
        SettingsDto settingsDto = settingsService.findByCurrentSettingsProfile();

        long lateCount = employeeLateCountByMonth(settingsService.findByCurrentSettingsProfile().getDefaultStartWork(),
                employeeId, yearMonth);
        long earlyLeavingCount = earlyLeavingCountByEmployeeAndPeriod(settingsService.findByCurrentSettingsProfile().getDefaultWorkTime(),
                employeeId, yearMonth);
        long absenceCount = absenceCountByEmployeeAndPeriod(employeeId, yearMonth);
        long skipCount = skipCountByEmployeeAndPeriod(employeeId, yearMonth);

        long maxLateCountByMonth = settingsDto.getMaxLateCountByMonth();
        long maxEarlyLivingCountByMonth = settingsDto.getMaxEarlyLivingCountByMonth();
        long maxAbsenceCountByMonth = settingsDto.getMaxAbsenceCountByMonth();
        long maxSkipCountByMonth = settingsDto.getMaxSkipCountByMonth();
        long maxDistractionTimeByDay = settingsDto.getMaxDistractionTimeByDay();
        long maxRestTimeByDay = settingsDto.getMaxRestTimeByDay();


        List<PrivilegeDto> allPrivileges = privilegeService.findAll();
        List<String> currentPrivileges = Arrays.asList(employeeDto.getPrivileges().split("; "));

        long increasedLate = DeviationFormatter.getIncreasedAmount(LATE_COUNT, allPrivileges);
        long increasedEarlyLeaving = DeviationFormatter.getIncreasedAmount(EARLY_LIVING_COUNT, allPrivileges);
        long increasedAbsence = DeviationFormatter.getIncreasedAmount(ABSENCE, allPrivileges);
        long increasedSkip = DeviationFormatter.getIncreasedAmount(SKIP, allPrivileges);
        long increasedRestTime = DeviationFormatter.getIncreasedAmount(REST_TIME, allPrivileges);
        long increasedDistractionTime = DeviationFormatter.getIncreasedAmount(DISTRACTION_TIME, allPrivileges);

        maxAbsenceCountByMonth = currentPrivileges.contains(ABSENCE.getName()) ?
                maxAbsenceCountByMonth + increasedAbsence : maxAbsenceCountByMonth;
        maxEarlyLivingCountByMonth = currentPrivileges.contains(EARLY_LIVING_COUNT.getName()) ?
                maxEarlyLivingCountByMonth + increasedEarlyLeaving : maxEarlyLivingCountByMonth;
        maxSkipCountByMonth = currentPrivileges.contains(SKIP.getName()) ?
                maxSkipCountByMonth + increasedSkip : maxSkipCountByMonth;
        maxLateCountByMonth = currentPrivileges.contains(LATE_COUNT.getName()) ?
                maxLateCountByMonth + increasedLate : maxLateCountByMonth;

        maxDistractionTimeByDay = currentPrivileges.contains(DISTRACTION_TIME.getName()) ?
                maxDistractionTimeByDay + increasedDistractionTime : maxDistractionTimeByDay;
        maxRestTimeByDay = currentPrivileges.contains(REST_TIME.getName()) ?
                maxRestTimeByDay + increasedRestTime : maxRestTimeByDay;

        long excessDistractionTimeCount = excessDistractionTimeCountByEmployeeAndPeriod(employeeId,
                yearMonth, maxDistractionTimeByDay);

        long excessRestTimeCount = excessRestTimeCountByEmployeeAndPeriod(employeeId,
                yearMonth, maxRestTimeByDay);
        long deviationCount = lateCount + earlyLeavingCount + absenceCount + skipCount + excessDistractionTimeCount
                + excessRestTimeCount;

        long workingDaysByMonth = SummaryFormatter.getWorkingDays(yearMonth.withDayOfMonth(1),
                yearMonth.withDayOfMonth(yearMonth.getMonth().length(yearMonth.isLeapYear())));

        double latePercent = SummaryFormatter.percentFormat(lateCount, maxLateCountByMonth);
        double earlyLeavingPercent = SummaryFormatter.percentFormat(earlyLeavingCount, maxEarlyLivingCountByMonth);
        double absencePercent = SummaryFormatter.percentFormat(absenceCount, maxAbsenceCountByMonth);
        double skipPercent = SummaryFormatter.percentFormat(skipCount, maxSkipCountByMonth);
        double excessDistractionTimePercent = SummaryFormatter.percentFormat(excessDistractionTimeCount, excessDistractionTimeCount * workingDaysByMonth);
        double excessRestTimePercent = SummaryFormatter.percentFormat(excessRestTimeCount, settingsService.findByCurrentSettingsProfile()
                .getMaxRestTimeByDay());

        deviationsSummaryDto.setEmployeeName(employeeDto.getName());
        deviationsSummaryDto.setDeviationCount(DeviationFormatter.format(deviationCount));
        deviationsSummaryDto.setLateCount(DeviationFormatter.format(lateCount, latePercent, DEFAULT_MAX));
        deviationsSummaryDto.setEarlyLeavingCount(DeviationFormatter.format(earlyLeavingCount, earlyLeavingPercent, DEFAULT_MAX));
        deviationsSummaryDto.setAbsenceCount(DeviationFormatter.format(absenceCount, absencePercent, DEFAULT_MAX));
        deviationsSummaryDto.setSkipCount(DeviationFormatter.format(skipCount, skipPercent, DEFAULT_MAX));
        deviationsSummaryDto.setExcessDistractionTimeCount(DeviationFormatter.format(excessDistractionTimeCount, excessDistractionTimePercent, DEFAULT_MAX));
        deviationsSummaryDto.setExcessRestTimeCount(DeviationFormatter.format(excessRestTimeCount, excessRestTimePercent, DEFAULT_MAX));
        deviationsSummaryDto.setPrivileges(String.join("; ", employeeDto.getPrivileges()));

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

    @Override
    public long employeeLateCountByMonth(LocalTime defaultStartWork, Long id, LocalDate yearMonth) {
        return deviationRepository.employeeLateCountByMonth(defaultStartWork, id, yearMonth)
                .orElse(0L);
    }

    @Override
    public long earlyLeavingCountByEmployeeAndPeriod(long defaultWorkTime, Long id, LocalDate yearMonth) {
        return deviationRepository.earlyLeavingCountByEmployeeAndPeriod(defaultWorkTime, id, yearMonth)
                .orElse(0L);
    }

    @Override
    public long absenceCountByEmployeeAndPeriod(Long id, LocalDate yearMonth) {
        return deviationRepository.absenceCountByEmployeeAndPeriod(id, yearMonth)
                .orElse(0L);
    }

    @Override
    public long skipCountByEmployeeAndPeriod(Long id, LocalDate yearMonth) {
        return deviationRepository.skipCountByEmployeeAndPeriod(id, yearMonth)
                .orElse(0L);
    }

    @Override
    public long excessDistractionTimeCountByEmployeeAndPeriod(Long id, LocalDate yearMonth, long maxDistractionTimeByDay) {
        return deviationRepository.excessDistractionTimeCountByEmployeeAndPeriod(id, yearMonth, maxDistractionTimeByDay)
                .orElse(0L);
    }

    @Override
    public long excessRestTimeCountByEmployeeAndPeriod(Long id, LocalDate yearMonth, long maxRestTimeByDay) {
        return deviationRepository.excessRestTimeCountByEmployeeAndPeriod(id, yearMonth, maxRestTimeByDay)
                .orElse(0L);
    }
}
