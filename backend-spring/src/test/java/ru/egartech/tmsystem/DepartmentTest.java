package ru.egartech.tmsystem;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.egartech.tmsystem.model.dto.*;
import ru.egartech.tmsystem.model.entity.Department;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.model.entity.Position;
import ru.egartech.tmsystem.model.entity.TimeSheet;
import ru.egartech.tmsystem.model.mapping.DepartmentMapper;
import ru.egartech.tmsystem.model.mapping.EmployeeMapper;
import ru.egartech.tmsystem.model.mapping.PositionMapper;
import ru.egartech.tmsystem.model.mapping.TimeSheetMapper;
import ru.egartech.tmsystem.model.repository.EmployeeRepository;
import ru.egartech.tmsystem.service.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class DepartmentTest {

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


    /**
     * Тест всех используемых CRUD операций
     */
    @Test
    public void crudTest() {
        DepartmentDto beforeServiceDto = new DepartmentDto("Security");
        //Save test
        DepartmentDto afterServiceDto = departmentService.save(beforeServiceDto);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(beforeServiceDto.getName())
                .describedAs(String.format("Проверяем, что имя сохраненной сущности %s", beforeServiceDto.getName()))
                .isEqualTo(afterServiceDto.getName());
        beforeServiceDto.setId(afterServiceDto.getId());
        //Update test
        beforeServiceDto.setName("IT");
        afterServiceDto = departmentService.updateById(beforeServiceDto.getId(), beforeServiceDto);
        softAssertions.assertThat(beforeServiceDto.getName())
                .describedAs(String.format("Проверяем, что имя обновленной сущности сущности %s", beforeServiceDto.getName()))
                .isEqualTo(afterServiceDto.getName());
        //FindById test
        beforeServiceDto = departmentService.findById(beforeServiceDto.getId());
        softAssertions.assertThat(beforeServiceDto.getName())
                .describedAs(String.format("Проверяем, что имя найденной по id сущности сущности %s", beforeServiceDto.getName()))
                .isEqualTo(afterServiceDto.getName());
        //FindAll and Delete test
        departmentService.deleteById(beforeServiceDto.getId());
        softAssertions.assertThat(departmentService.findAll())
                .describedAs("Проверяем, что список пустой")
                .isEqualTo(Collections.EMPTY_LIST);
        softAssertions.assertAll();
    }

    @BeforeAll
    static void setup() {

    }

    /**
     * Проверяем, что правильно считается суммарное время отвлечений, перерывов и отработанного времени по всему отделу
     */
    @Test
    public void departmentSummaryTimeByPeriodTest() {
        LocalDate startDate = LocalDate.of(2023, 10, 18);
        LocalDate endDate = LocalDate.of(2023, 10, 21);

        LocalDate date1 = LocalDate.of(2023, 10, 19);
        LocalDate date2 = LocalDate.of(2023, 10, 20);

        LocalTime startWork = LocalTime.of(9, 0);
        LocalTime endWork = LocalTime.of(18, 0);

        LocalTime startRest = LocalTime.of(9, 15);
        LocalTime endRest = LocalTime.of(9, 30);

        LocalTime startDistraction = LocalTime.of(10, 0);
        LocalTime endDistraction = LocalTime.of(10, 40);

        Long workTime = Duration.between(startWork, endWork).toMinutes() * 2;
        Long restTime = Duration.between(startRest, endRest).toMinutes() * 2;
        Long distractionTime = Duration.between(startDistraction, endDistraction).toMinutes() * 2;

        Department department = departmentMapper.toEntity(departmentService.save(new DepartmentDto("IT")));

        Position position1 = positionMapper.toEntity(positionService.save(positionMapper.toDto(new Position("QA", department))));
        Position position2 = positionMapper.toEntity(positionService.save(positionMapper.toDto(new Position("TeamLead", department))));

        Employee employee1 = employeeMapper.toEntity(employeeService.save(new EmployeeDto("Petr", 29, position1)));
        Employee employee2 = employeeMapper.toEntity(employeeService.save(new EmployeeDto("Ivan", 32, position2)));


        TimeSheetDto timeSheet1 = new TimeSheetDto(date1, startWork, endWork, employee1);
        TimeSheetDto timeSheet2 = new TimeSheetDto(date2, startWork, endWork, employee2);
        RestDto restDto1 = new RestDto(date1, startRest, endRest, employee1);
        RestDto restDto2 = new RestDto(date2, startRest, endRest, employee2);
        DistractionDto distractionDto1 = new DistractionDto(date1, startDistraction, endDistraction, employee1);
        DistractionDto distractionDto2 = new DistractionDto(date2, startDistraction, endDistraction, employee2);


        restService.save(restDto1);
        restService.save(restDto2);

        distractionService.save(distractionDto1);
        distractionService.save(distractionDto2);

        timeSheetService.save(timeSheet1);
        timeSheetService.save(timeSheet2);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(departmentService.departmentWorkTimeByPeriod(startDate, endDate, department.getId()))
                .describedAs(String.format("Проверяем, что суммарное рабочее время %d мин", workTime))
                .isEqualTo(1080);

        softAssertions.assertThat(departmentService.departmentRestTimeByPeriod(startDate, endDate, department.getId()))
                .describedAs(String.format("Проверяем, что суммарное время перерывов %d мин", restTime))
                .isEqualTo(restTime);

        softAssertions.assertThat(departmentService.departmentDistractionTimeByPeriod(startDate, endDate, department.getId()))
                .describedAs(String.format("Проверяем, что суммарное время отвлечений %d мин", distractionTime))
                .isEqualTo(distractionTime);

        List<RestDto> r = restService.findAll();

        softAssertions.assertAll();
    }
}