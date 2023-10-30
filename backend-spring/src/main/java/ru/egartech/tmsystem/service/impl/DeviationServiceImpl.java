package ru.egartech.tmsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.model.dto.DeviationSummaryDto;
import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.dto.PrivilegeDto;
import ru.egartech.tmsystem.model.dto.SettingsDto;
import ru.egartech.tmsystem.model.mapping.DeviationMapper;
import ru.egartech.tmsystem.model.repository.DeviationRepository;
import ru.egartech.tmsystem.service.DeviationService;
import ru.egartech.tmsystem.service.EmployeeService;
import ru.egartech.tmsystem.service.PrivilegeService;
import ru.egartech.tmsystem.service.SettingsService;
import ru.egartech.tmsystem.utils.DeviationFormatter;
import ru.egartech.tmsystem.utils.SummaryFormatter;

import java.time.LocalDate;
import java.time.LocalTime;
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
    private final SettingsService settingsService;
    private final DeviationRepository deviationRepository;
    private final DeviationMapper mapper;

    @Override
    public DeviationSummaryDto deviationEmployeeByMonth(LocalDate yearMonth, Long employeeId) {
        EmployeeDto employeeDto = employeeService.findById(employeeId);
        SettingsDto settingsDto = settingsService.findByCurrentSettingsProfile();
        YearMonth month = YearMonth.from(yearMonth);
        LocalDate startDate = month.atDay(1);
        LocalDate endDate = month.atEndOfMonth();
        List<PrivilegeDto> allPrivileges = privilegeService.findAll();
        List<String> currentPrivileges = new ArrayList<>();
        if (employeeDto.getPrivilegesNumber() == null) {
            currentPrivileges.add("");
        } else {
            currentPrivileges = Arrays.asList(employeeDto.getPrivileges().split("; "));
        }

        long lateCount = employeeLateCountByMonth(settingsDto.getDefaultStartWork(), employeeId, startDate, endDate);
        long earlyLeavingCount = earlyLeavingCountByEmployeeAndPeriod(settingsDto.getDefaultWorkTime(), employeeId, startDate, endDate);
        long absenceCount = absenceCountByEmployeeAndPeriod(employeeId, startDate, endDate);
        long skipCount = skipCountByEmployeeAndPeriod(employeeId, startDate, endDate);
        long excessDistractionTimeCount = excessDistractionTimeCount(settingsDto, currentPrivileges, allPrivileges, startDate, endDate, employeeId);
        long excessRestTimeCount = excessRestTimeCount(settingsDto, currentPrivileges, allPrivileges, startDate, endDate, employeeId);

        return mapper.toDeviationSummaryDto(
                employeeDto,
                lateCount,
                latePercent(settingsDto, lateCount, currentPrivileges, allPrivileges),
                earlyLeavingCount,
                earlyLeavingPercent(settingsDto, earlyLeavingCount, currentPrivileges, allPrivileges),
                absenceCount,
                absencePercent(settingsDto, absenceCount, currentPrivileges, allPrivileges),
                skipCount,
                skipPercent(settingsDto, skipCount, currentPrivileges, allPrivileges),
                excessDistractionTimeCount,
                excessDistractionTimePercent(settingsDto, excessDistractionTimeCount),
                excessRestTimeCount,
                excessRestTimePercent(settingsDto, excessRestTimeCount));
    }

    @Override
    public double latePercent(SettingsDto settingsDto, Long lateCount,
                              List<String> currentPrivileges, List<PrivilegeDto> allPrivileges) {
        long maxLateCountByMonth = settingsDto.getMaxLateCountByMonth();
        long increasedLate = DeviationFormatter.getIncreasedAmount(LATE_COUNT, allPrivileges);
        maxLateCountByMonth = currentPrivileges.contains(LATE_COUNT.getName()) ?
                maxLateCountByMonth + increasedLate : maxLateCountByMonth;
        return SummaryFormatter.percentFormat(lateCount, maxLateCountByMonth);
    }

    @Override
    public double earlyLeavingPercent(SettingsDto settingsDto, Long earlyLeavingCount,
                                      List<String> currentPrivileges, List<PrivilegeDto> allPrivileges) {
        long maxEarlyLivingCountByMonth = settingsDto.getMaxEarlyLivingCountByMonth();
        long increasedEarlyLeaving = DeviationFormatter.getIncreasedAmount(EARLY_LIVING_COUNT, allPrivileges);
        maxEarlyLivingCountByMonth = currentPrivileges.contains(EARLY_LIVING_COUNT.getName()) ?
                maxEarlyLivingCountByMonth + increasedEarlyLeaving : maxEarlyLivingCountByMonth;
        return SummaryFormatter.percentFormat(earlyLeavingCount, maxEarlyLivingCountByMonth);
    }

    @Override
    public double absencePercent(SettingsDto settingsDto, Long absenceCount,
                                 List<String> currentPrivileges, List<PrivilegeDto> allPrivileges) {
        long maxAbsenceCountByMonth = settingsDto.getMaxAbsenceCountByMonth();
        long increasedAbsence = DeviationFormatter.getIncreasedAmount(ABSENCE, allPrivileges);
        maxAbsenceCountByMonth = currentPrivileges.contains(ABSENCE.getName()) ?
                maxAbsenceCountByMonth + increasedAbsence : maxAbsenceCountByMonth;
        return SummaryFormatter.percentFormat(absenceCount, maxAbsenceCountByMonth);
    }

    @Override
    public double skipPercent(SettingsDto settingsDto, Long skipCount,
                              List<String> currentPrivileges, List<PrivilegeDto> allPrivileges) {
        long maxSkipCountByMonth = settingsDto.getMaxSkipCountByMonth();
        long increasedSkip = DeviationFormatter.getIncreasedAmount(SKIP, allPrivileges);
        maxSkipCountByMonth = currentPrivileges.contains(SKIP.getName()) ?
                maxSkipCountByMonth + increasedSkip : maxSkipCountByMonth;
        return SummaryFormatter.percentFormat(skipCount, maxSkipCountByMonth);
    }

    @Override
    public long excessDistractionTimeCount(SettingsDto settingsDto, List<String> currentPrivileges,
                                           List<PrivilegeDto> allPrivileges, LocalDate startDate, LocalDate endDate,
                                           Long employeeId) {
        long maxDistractionTimeByDay = settingsDto.getMaxDistractionTimeByDay();
        long increasedDistractionTime = DeviationFormatter.getIncreasedAmount(DISTRACTION_TIME, allPrivileges);
        maxDistractionTimeByDay = currentPrivileges.contains(DISTRACTION_TIME.getName()) ?
                maxDistractionTimeByDay + increasedDistractionTime : maxDistractionTimeByDay;
        return excessDistractionTimeCountByEmployeeAndPeriod(employeeId,
                startDate, endDate, maxDistractionTimeByDay);
    }

    @Override
    public double excessDistractionTimePercent(SettingsDto settingsDto, long excessDistractionTimeCount) {
        long maxExcessDistractionTimeByMonth = settingsDto.getMaxExcessDistractionCountByMonth();
        return SummaryFormatter.percentFormat(excessDistractionTimeCount, maxExcessDistractionTimeByMonth);
    }

    @Override
    public long excessRestTimeCount(SettingsDto settingsDto, List<String> currentPrivileges,
                                    List<PrivilegeDto> allPrivileges, LocalDate startDate, LocalDate endDate,
                                    Long employeeId) {
        long maxRestTimeByDay = settingsDto.getMaxRestTimeByDay();
        long increasedRestTime = DeviationFormatter.getIncreasedAmount(REST_TIME, allPrivileges);
        maxRestTimeByDay = currentPrivileges.contains(REST_TIME.getName()) ?
                maxRestTimeByDay + increasedRestTime : maxRestTimeByDay;
        return excessRestTimeCountByEmployeeAndPeriod(employeeId,
                startDate, endDate, maxRestTimeByDay);
    }

    @Override
    public double excessRestTimePercent(SettingsDto settingsDto, long excessRestTimeCount) {
        long maxExcessRestTimeByMonth = settingsDto.getMaxExcessRestCountByMonth();
        return SummaryFormatter.percentFormat(excessRestTimeCount, maxExcessRestTimeByMonth);
    }

    @Override
    public List<DeviationSummaryDto> deviationAllEmployeesByMonth(LocalDate yearMonth) {
        List<DeviationSummaryDto> deviations = new ArrayList<>();
        List<EmployeeDto> employees = employeeService.findAll();
        employees.forEach(e -> deviations.add(deviationEmployeeByMonth(yearMonth, e.getId())));
        return deviations;
    }

    @Override
    public long employeeLateCountByMonth(LocalTime defaultStartWork, Long id, LocalDate startDate, LocalDate endDate) {
        return deviationRepository.employeeLateCountByMonth(defaultStartWork, id, startDate, endDate)
                .orElse(0L);
    }

    @Override
    public long earlyLeavingCountByEmployeeAndPeriod(long defaultWorkTime, Long id, LocalDate startDate, LocalDate endDate) {
        return deviationRepository.earlyLeavingCountByEmployeeAndPeriod(defaultWorkTime, id, startDate, endDate)
                .orElse(0L);
    }

    @Override
    public long absenceCountByEmployeeAndPeriod(Long id, LocalDate startDate, LocalDate endDate) {
        return deviationRepository.absenceCountByEmployeeAndPeriod(id, startDate, endDate)
                .orElse(0L);
    }

    @Override
    public long skipCountByEmployeeAndPeriod(Long id, LocalDate startDate, LocalDate endDate) {
        return deviationRepository.skipCountByEmployeeAndPeriod(id, startDate, endDate)
                .orElse(0L);
    }

    @Override
    public long excessDistractionTimeCountByEmployeeAndPeriod(Long id, LocalDate startDate, LocalDate endDate, long maxDistractionTimeByDay) {
        return deviationRepository.excessDistractionTimeCountByEmployeeAndPeriod(id, startDate, endDate, maxDistractionTimeByDay)
                .orElse(0L);
    }

    @Override
    public long excessRestTimeCountByEmployeeAndPeriod(Long id, LocalDate startDate, LocalDate endDate, long maxRestTimeByDay) {
        return deviationRepository.excessRestTimeCountByEmployeeAndPeriod(id, startDate, endDate, maxRestTimeByDay)
                .orElse(0L);
    }
}
