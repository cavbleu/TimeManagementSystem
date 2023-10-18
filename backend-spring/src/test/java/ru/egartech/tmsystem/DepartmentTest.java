package ru.egartech.tmsystem;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.egartech.tmsystem.model.dto.DepartmentDto;
import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.dto.PositionDto;
import ru.egartech.tmsystem.model.dto.TimeSheetDto;
import ru.egartech.tmsystem.model.entity.Department;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.model.entity.Position;
import ru.egartech.tmsystem.model.entity.TimeSheet;
import ru.egartech.tmsystem.model.mapping.DepartmentMapper;
import ru.egartech.tmsystem.model.mapping.TimeSheetMapper;
import ru.egartech.tmsystem.service.DepartmentService;
import ru.egartech.tmsystem.service.EmployeeService;
import ru.egartech.tmsystem.service.PositionService;
import ru.egartech.tmsystem.service.TimeSheetService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class DepartmentTest {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private TimeSheetService timeSheetService;
    @Autowired
    TimeSheetMapper timeSheetMapper;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PositionService positionService;

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
                .describedAs(String.format("Проверяем, что имя найденной сущности сущности %s", beforeServiceDto.getName()))
                .isEqualTo(afterServiceDto.getName());
        //FindAll and Delete test
        departmentService.deleteById(beforeServiceDto.getId());
        softAssertions.assertThat(departmentService.findAll())
                .describedAs("Проверяем, что список пустой")
                .isEqualTo(Collections.EMPTY_LIST);
        softAssertions.assertAll();
    }

    @Test
    public void getDepartmentWorkTimeByPeriodTest() {
        LocalDate startDate = LocalDate.of(2023, 10, 18);
        LocalDate endDate = LocalDate.of(2023, 10, 21);
        Department department = departmentMapper.toEntity(departmentService.save(departmentMapper.toDto(new Department("IT"))));

        TimeSheet timeSheet1 = new TimeSheet(LocalDate.of(2023, 10, 19), 540L,
                new Employee("Petr", 29, new Position("QA", department)));
        TimeSheet timeSheet2 = new TimeSheet(LocalDate.of(2023, 10, 20), 540L,
                new Employee("Ivan", 32, new Position("TeamLead", department)));
        timeSheetService.save(timeSheetMapper.toDto(timeSheet1));
        timeSheetService.save(timeSheetMapper.toDto(timeSheet2));
        List<TimeSheetDto> t = timeSheetService.findAll();
        List<PositionDto> p = positionService.findAll();
        List<EmployeeDto> e = employeeService.findAll();
        List<DepartmentDto> d = departmentService.findAll();

        Assertions.assertEquals(1080L, departmentService.departmentWorkTimeByPeriod(startDate, endDate, department.getId()));
    }
}