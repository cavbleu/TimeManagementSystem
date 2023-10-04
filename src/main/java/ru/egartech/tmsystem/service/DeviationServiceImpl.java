package ru.egartech.tmsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.model.dto.DeviationDto;
import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.dto.EntityDto;
import ru.egartech.tmsystem.model.dto.FilterDto;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.model.repository.DeviationRepository;
import ru.egartech.tmsystem.utils.DeviationFormatter;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviationServiceImpl implements DeviationService {

    private final EmployeeService employeeService;
    private final DeviationRepository deviationRepository;
    private final SettingsService settingsService;

    @Override
    public DeviationDto deviationEmployeeByMonth(FilterDto filter, Long employeeId) {

        DeviationDto deviationsSummaryDto = new DeviationDto();
        EmployeeDto employeeDto = employeeService.findById(employeeId)
                .orElseThrow();

        long lateCount = deviationRepository.employeeLateCountByMonth(settingsService.findByCurrentSettingsProfile().getDefaultStartWork(),
                employeeId, YearMonth.from(filter.getYearMonth()));
        long earlyLeavingCount = deviationRepository.earlyLeavingCountByEmployeeAndPeriod(settingsService.findByCurrentSettingsProfile().getDefaultWorkTime(),
                employeeId, YearMonth.from(filter.getYearMonth()));
        long absenceCount = deviationRepository.absenceCountByEmployeeAndPeriod(employeeId, YearMonth.from(filter.getYearMonth()));
        long skipCount = deviationRepository.skipCountByEmployeeAndPeriod(employeeId, YearMonth.from(filter.getYearMonth()));
        long excessDistractionTimeCount = deviationRepository.excessDistractionTimeCountByEmployeeAndPeriod(employeeId,
                YearMonth.from(filter.getYearMonth()), settingsService.findByCurrentSettingsProfile().getMaxDistractionTimeByDay());
        long excessLunchTimeCount = deviationRepository.excessLunchTimeCountByEmployeeAndPeriod(employeeId,
                YearMonth.from(filter.getYearMonth()), settingsService.findByCurrentSettingsProfile().getMaxLunchTimeByDay());
        long excessRestTimeCount = deviationRepository.excessRestTimeCountByEmployeeAndPeriod(employeeId,
                YearMonth.from(filter.getYearMonth()), settingsService.findByCurrentSettingsProfile().getMaxRestTimeByDay());
        long deviationCount = lateCount + earlyLeavingCount + absenceCount + skipCount + excessDistractionTimeCount +
                excessLunchTimeCount + excessRestTimeCount;

        double latePercent = (double) lateCount * 100 / settingsService.findByCurrentSettingsProfile()
                .getMaxLateCountByMonth();
        double earlyLeavingPercent = (double) earlyLeavingCount * 100 / settingsService.findByCurrentSettingsProfile()
                .getMaxEarlyLivingCountByMonth();
        double absencePercent = (double) absenceCount * 100 / settingsService.findByCurrentSettingsProfile()
                .getMaxAbsenceCountByMonth();
        double skipPercent = (double) skipCount * 100 / settingsService.findByCurrentSettingsProfile()
                .getMaxSkipWorkCountByMonth();
        double excessDistractionTimePercent = (double) excessDistractionTimeCount * 100 / settingsService.findByCurrentSettingsProfile()
                .getMaxDistractionTimeByDay();
        double excessLunchTimePercent = (double) excessLunchTimeCount * 100 / settingsService.findByCurrentSettingsProfile()
                .getMaxLunchTimeByDay();
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
