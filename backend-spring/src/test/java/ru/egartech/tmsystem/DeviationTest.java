package ru.egartech.tmsystem;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.egartech.tmsystem.model.dto.*;
import ru.egartech.tmsystem.model.entity.Department;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.model.entity.Position;
import ru.egartech.tmsystem.model.mapping.DepartmentMapper;
import ru.egartech.tmsystem.model.mapping.EmployeeMapper;
import ru.egartech.tmsystem.model.mapping.PositionMapper;
import ru.egartech.tmsystem.model.repository.EmployeeRepository;
import ru.egartech.tmsystem.service.*;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
@DirtiesContext
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

    Employee employee;
    LocalDate startDate;
    LocalDate endDate;

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

        Department department = departmentMapper.toEntity(departmentService.save(new DepartmentDto("IT")));

        Position position = positionMapper.toEntity(positionService.save(new PositionDto("QA", department)));

        employee = employeeMapper.toEntity(employeeService.save(new EmployeeDto("Petr", 29, position)));

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
    @DisplayName("Тест - общее число превышений времени перерывов")
    @DirtiesContext
    void excessRestTimeCountByEmployeeAndPeriodTest() {
        long maxTimeByDay = 1;
        int expected = 2;
        Assertions.assertThat(deviationService.excessRestTimeCountByEmployeeAndPeriod(employee.getId(), startDate, endDate, maxTimeByDay))
                .describedAs(String.format("Проверяем, что общее число превышений времени перерывов равно %d", expected))
                .isEqualTo(expected);

    }

    @Test
    @DisplayName("Тест - общее число превышений времени отвлечений")
    @DirtiesContext
    void excessDistractionTimeCountByEmployeeAndPeriodTest() {
        long maxTimeByDay = 1;
        int expected = 2;
        Assertions.assertThat(deviationService.excessDistractionTimeCountByEmployeeAndPeriod(employee.getId(), startDate, endDate, maxTimeByDay))
                .describedAs(String.format("Проверяем, что общее число превышений времени отвлечений равно %d", expected))
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Тест - общее число превышений прогулов")
    @DirtiesContext
    void skipCountByEmployeeAndPeriodTest() {
        int expected = 1;
        Assertions.assertThat(deviationService.skipCountByEmployeeAndPeriod(employee.getId(), startDate, endDate))
                .describedAs(String.format("Проверяем, что общее число превышений прогулов равно %d", expected))
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Тест - общее число превышений отсутствий")
    @DirtiesContext
    void absenceCountByEmployeeAndPeriod() {
        int expected = 1;
        Assertions.assertThat(deviationService.absenceCountByEmployeeAndPeriod(employee.getId(), startDate, endDate))
                .describedAs(String.format("Проверяем, что общее число превышений отсутствий равно %d", expected))
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Тест - общее число превышений ранних уходов с работы")
    @DirtiesContext
    void earlyLeavingCountByEmployeeAndPeriodTest() {
        long defaultWorkTime = 900;
        int expected = 2;
        Assertions.assertThat(deviationService.earlyLeavingCountByEmployeeAndPeriod(defaultWorkTime, employee.getId(),
                        startDate, endDate))
                .describedAs(String.format("Проверяем, что общее число превышений ранних уходов с работы равно %d", expected))
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Тест - общее число превышений опозданий")
    @DirtiesContext
    void employeeLateCountByMonthTest() {
        LocalTime defaultStartWork = LocalTime.of(7, 0);
        int expected = 2;
        Assertions.assertThat(deviationService.employeeLateCountByMonth(defaultStartWork, employee.getId(),
                        startDate, endDate))
                .describedAs(String.format("Проверяем, что общее число превышений опозданий равно %d", expected))
                .isEqualTo(expected);
    }
}
