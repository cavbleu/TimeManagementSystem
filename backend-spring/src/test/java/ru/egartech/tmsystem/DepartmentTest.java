package ru.egartech.tmsystem;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.egartech.tmsystem.model.dto.*;
import ru.egartech.tmsystem.model.entity.Department;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.model.entity.Position;
import ru.egartech.tmsystem.model.entity.TimeSheet;
import ru.egartech.tmsystem.model.mapping.DepartmentMapper;
import ru.egartech.tmsystem.model.mapping.TimeSheetMapper;
import ru.egartech.tmsystem.service.*;

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
    private DistractionService distractionService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TimeSheetService timeSheetService;
    @Autowired
    DepartmentMapper departmentMapper;


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

        Department department = departmentMapper.toEntity(departmentService.save(new DepartmentDto("IT")));
        Position position1 = new Position("QA", department);
        Position position2 = new Position("TeamLead", department);
        EmployeeDto employeeDto1 = new EmployeeDto("Petr", 29, position1);
        EmployeeDto employeeDto2 = new EmployeeDto("Ivan", 32, position2);
        Employee employee1 = new Employee("Petr", 29, position1);
        Employee employee2 = new Employee("Ivan", 32, position2);
        TimeSheetDto timeSheet1 = new TimeSheetDto(date1, startWork, endWork, employeeDto1);
        TimeSheetDto timeSheet2 = new TimeSheetDto(date2, startWork, endWork, employeeDto2);
        RestDto restDto1 = new RestDto(date1, startRest, endRest, employee1);
        RestDto restDto2 = new RestDto(date2, startRest, endRest, employee2);
        DistractionDto distractionDto1 = new DistractionDto(date1, startDistraction, endDistraction, employee1);
        DistractionDto distractionDto2 = new DistractionDto(date2, startDistraction, endDistraction, employee2);

        employeeService.save(employeeDto1);
        employeeService.save(employeeDto2);

        restService.save(restDto1);
        restService.save(restDto2);

        distractionService.save(distractionDto1);
        distractionService.save(distractionDto2);

        timeSheetService.save(timeSheet1);
        timeSheetService.save(timeSheet2);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(departmentService.departmentWorkTimeByPeriod(startDate, endDate, department.getId()))
                .describedAs(String.format("Проверяем, что суммарное рабочее время %d мин", 1080))
                .isEqualTo(1080L);

    }
}