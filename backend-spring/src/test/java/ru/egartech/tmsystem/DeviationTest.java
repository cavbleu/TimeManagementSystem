package ru.egartech.tmsystem;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.egartech.tmsystem.model.dto.*;
import ru.egartech.tmsystem.model.enumeration.EmployeePrivilegesEnum;
import ru.egartech.tmsystem.model.mapping.DepartmentMapper;
import ru.egartech.tmsystem.model.mapping.EmployeeMapper;
import ru.egartech.tmsystem.model.mapping.PositionMapper;
import ru.egartech.tmsystem.model.repository.EmployeeRepository;
import ru.egartech.tmsystem.service.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DeviationTest {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private RestService restService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private DistractionService distractionService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TimeSheetService timeSheetService;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    PositionMapper positionMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DeviationService deviationService;
    @Autowired
    SettingsService settingsService;
    @Autowired
    PrivilegeService privilegeService;

    EmployeeDto employee;
    LocalDate startDate;
    LocalDate endDate;
    SettingsDto settings;
    PrivilegeDto privilege;
    List<PrivilegeDto> allPrivileges;

    @BeforeEach
    void init() {
        startDate = LocalDate.of(2023, 10, 18);
        endDate = LocalDate.of(2023, 10, 21);

        LocalDate date1 = LocalDate.of(2023, 10, 19);
        LocalDate date2 = LocalDate.of(2023, 10, 20);

        LocalTime startWork = LocalTime.of(9, 0);
        LocalTime endWork = LocalTime.of(18, 0);

        LocalTime startRest = LocalTime.of(9, 15);
        LocalTime endRest = LocalTime.of(9, 30);

        LocalTime startDistraction = LocalTime.of(10, 0);
        LocalTime endDistraction = LocalTime.of(10, 40);

        String absenceReason = "Устал";

        String name = "Стандартный профиль";
        boolean currentSettingsProfile = true;
        LocalTime defaultStartWork = LocalTime.of(7, 0);
        long defaultWorkTime = 900;
        long maxLateCountByMonth = 3;
        long maxEarlyLivingCountByMonth = 2;
        long maxAbsenceCountByMonth = 1;
        long maxSkipCountByMonth = 1;
        long maxDistractionTimeByDay = 1;
        long maxRestTimeByDay = 1;
        long maxExcessDistractionCountByMonth = 3;
        long maxExcessRestCountByMonth = 3;

        DepartmentDto department = departmentService.save(new DepartmentDto("IT"));

        PositionDto position = positionService.save(new PositionDto("QA", department));

        employee = employeeService.save(new EmployeeDto("Petr", 29, position));

        settings = settingsService.save(new SettingsDto(name, currentSettingsProfile, defaultWorkTime, defaultStartWork, maxLateCountByMonth,
                maxEarlyLivingCountByMonth, maxAbsenceCountByMonth, maxSkipCountByMonth, maxDistractionTimeByDay, maxRestTimeByDay,
                maxExcessDistractionCountByMonth, maxExcessRestCountByMonth));


        privilege = privilegeService.save(new PrivilegeDto(EmployeePrivilegesEnum.LATE_COUNT.getName(), 1L));
        privilege = privilegeService.save(new PrivilegeDto(EmployeePrivilegesEnum.EARLY_LIVING_COUNT.getName(), 2L));
        privilege = privilegeService.save(new PrivilegeDto(EmployeePrivilegesEnum.ABSENCE.getName(), 1L));
        privilege = privilegeService.save(new PrivilegeDto(EmployeePrivilegesEnum.SKIP.getName(), 1L));
        privilege = privilegeService.save(new PrivilegeDto(EmployeePrivilegesEnum.REST_TIME.getName(), 40L));
        privilege = privilegeService.save(new PrivilegeDto(EmployeePrivilegesEnum.DISTRACTION_TIME.getName(), 40L));

        allPrivileges = privilegeService.findAll();

        TimeSheetDto timeSheet1 = new TimeSheetDto(date1, startWork, endWork, employee, absenceReason);
        TimeSheetDto timeSheet2 = new TimeSheetDto(date2, startWork, endWork, employee);
        RestDto restDto1 = new RestDto(date1, startRest, endRest, employee);
        RestDto restDto2 = new RestDto(date2, startRest, endRest, employee);
        DistractionDto distractionDto1 = new DistractionDto(date1, startDistraction, endDistraction, employee);
        DistractionDto distractionDto2 = new DistractionDto(date2, startDistraction, endDistraction, employee);

        restService.save(restDto1);
        restService.save(restDto2);

        distractionService.save(distractionDto1);
        distractionService.save(distractionDto2);

        timeSheetService.save(timeSheet1);
        timeSheetService.save(timeSheet2);
    }

    @Test
    @DisplayName("Общее число превышений времени перерывов")
    void excessRestTimeCountByEmployeeAndPeriodTest() {
        int expected = 2;
        Assertions.assertThat(deviationService.excessRestTimeCountByEmployeeAndPeriod(employee.getId(), startDate, endDate, settings.getMaxRestTimeByDay()))
                .describedAs(String.format("Проверяем, что общее число превышений времени перерывов равно %d", expected))
                .isEqualTo(expected);

    }

    @Test
    @DisplayName("Общее число превышений времени отвлечений")
    void excessDistractionTimeCountByEmployeeAndPeriodTest() {
        int expected = 2;
        Assertions.assertThat(deviationService.excessDistractionTimeCountByEmployeeAndPeriod(employee.getId(), startDate, endDate, settings.getMaxDistractionTimeByDay()))
                .describedAs(String.format("Проверяем, что общее число превышений времени отвлечений равно %d", expected))
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Общее число прогулов")
    void skipCountByEmployeeAndPeriodTest() {
        int expected = 1;
        Assertions.assertThat(deviationService.skipCountByEmployeeAndPeriod(employee.getId(), startDate, endDate))
                .describedAs(String.format("Проверяем, что общее число превышений прогулов равно %d", expected))
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Общее число отсутствий")
    void absenceCountByEmployeeAndPeriod() {
        int expected = 1;
        Assertions.assertThat(deviationService.absenceCountByEmployeeAndPeriod(employee.getId(), startDate, endDate))
                .describedAs(String.format("Проверяем, что общее число превышений отсутствий равно %d", expected))
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Общее число ранних уходов с работы")
    void earlyLeavingCountByEmployeeAndPeriodTest() {
        int expected = 2;
        Assertions.assertThat(deviationService.earlyLeavingCountByEmployeeAndPeriod(settings.getDefaultWorkTime(), employee.getId(),
                        startDate, endDate))
                .describedAs(String.format("Проверяем, что общее число превышений ранних уходов с работы равно %d", expected))
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Общее число опозданий")
    void employeeLateCountByMonthTest() {
        LocalTime defaultStartWork = LocalTime.of(7, 0);
        int expected = 2;
        Assertions.assertThat(deviationService.employeeLateCountByMonth(defaultStartWork, employee.getId(),
                        startDate, endDate))
                .describedAs(String.format("Проверяем, что общее число превышений опозданий равно %d", expected))
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Расчет процента опозданий")
    void employeeLatePercentTest() {
        int expected = 50;
        SettingsDto settingsDto = settingsService.findByCurrentSettingsProfile();
        List<PrivilegeDto> allPrivileges = privilegeService.findAll();
        List<String> currentPrivileges = List.of(EmployeePrivilegesEnum.LATE_COUNT.getName());
        long lateCount = deviationService.employeeLateCountByMonth(settingsDto.getDefaultStartWork(), employee.getId(),
                startDate, endDate);
        Assertions.assertThat(deviationService.latePercent(settingsDto, lateCount, currentPrivileges, allPrivileges))
                .describedAs(String.format("Проверяем, что процент от максимального количества опозданий равен %d%%", expected))
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Расчет процента ранних уходов")
    void employeeEarlyLeavingPercentTest() {
        int expected = 50;
        List<String> currentPrivileges = List.of(EmployeePrivilegesEnum.EARLY_LIVING_COUNT.getName());
        long earlyLeavingCount = deviationService.earlyLeavingCountByEmployeeAndPeriod(settings.getDefaultWorkTime(), employee.getId(),
                startDate, endDate);
        Assertions.assertThat(deviationService.earlyLeavingPercent(settings, earlyLeavingCount, currentPrivileges, allPrivileges))
                .describedAs(String.format("Проверяем, что процент от максимального количества ранних уходов равен %d%%", expected))
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Расчет процента отсутствий")
    void employeeAbsencePercentTest() {
        int expected = 50;
        List<String> currentPrivileges = List.of(EmployeePrivilegesEnum.ABSENCE.getName());
        long absenceCount = deviationService.absenceCountByEmployeeAndPeriod(employee.getId(), startDate, endDate);
        Assertions.assertThat(deviationService.absencePercent(settings, absenceCount, currentPrivileges, allPrivileges))
                .describedAs(String.format("Проверяем, что процент от максимального количества отсутствий равен %d%%", expected))
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Расчет процента прогулов")
    void employeeSkipPercentTest() {
        int expected = 50;
        List<String> currentPrivileges = List.of(EmployeePrivilegesEnum.ABSENCE.getName());
        long skipCount = deviationService.skipCountByEmployeeAndPeriod(employee.getId(), startDate, endDate);
        Assertions.assertThat(deviationService.absencePercent(settings, skipCount, currentPrivileges, allPrivileges))
                .describedAs(String.format("Проверяем, что процент от максимального количества прогулов равен %d%%", expected))
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Расчет процента превышений времени отвлечений")
    void excessDistractionPercentTest() {
        int expected = 0;
        List<String> currentPrivileges = List.of(EmployeePrivilegesEnum.DISTRACTION_TIME.getName());
        long excessDistractionTimeCount = deviationService.excessDistractionTimeCount(settings, currentPrivileges, allPrivileges, startDate, endDate, employee.getId());
        Assertions.assertThat(deviationService.excessDistractionTimePercent(settings, excessDistractionTimeCount))
                .describedAs(String.format("Проверяем, что процент от превышений времени отвлечений равен %d%%", expected))
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Расчет процента превышений времени перерывов")
    void excessRestPercentTest() {
        int expected = 0;
        List<String> currentPrivileges = List.of(EmployeePrivilegesEnum.REST_TIME.getName());
        long excessRestTimeCount = deviationService.excessRestTimeCount(settings, currentPrivileges, allPrivileges, startDate, endDate, employee.getId());
        Assertions.assertThat(deviationService.excessRestTimePercent(settings, excessRestTimeCount))
                .describedAs(String.format("Проверяем, что процент от  превышений времени перерывов равен %d%%", expected))
                .isEqualTo(expected);
    }


}
