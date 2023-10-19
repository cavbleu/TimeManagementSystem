package ru.egartech.tmsystem;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
import java.util.Collections;

@SpringBootTest
public class DistractionTest implements BaseTest {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DistractionService distractionService;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    PositionMapper positionMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    @Test
    public void crudTest() {
        LocalDate date = LocalDate.of(2023, 10, 19);

        LocalTime startDistraction = LocalTime.of(10, 0);
        LocalTime endDistraction = LocalTime.of(10, 40);

        Department department = departmentMapper.toEntity(departmentService.save(new DepartmentDto("IT")));
        Position position = positionMapper.toEntity(positionService.save(new PositionDto("QA", department)));
        Employee employee1 = employeeMapper.toEntity(employeeService.save(new EmployeeDto("Petr", 29, position)));
        Employee employee2 = employeeMapper.toEntity(employeeService.save(new EmployeeDto("Ivan", 32, position)));
        SoftAssertions softAssertions = new SoftAssertions();

        //Save test
        DistractionDto beforeServiceDto = new DistractionDto(date, startDistraction, endDistraction, employee1);
        DistractionDto afterServiceDto = distractionService.save(beforeServiceDto);
        softAssertions.assertThat(beforeServiceDto.getEmployee())
                .describedAs(String.format("Проверяем, что соответствующий отвлечению работник %s", beforeServiceDto.getEmployee()))
                .isEqualTo(afterServiceDto.getEmployee());

        //FindById test
        beforeServiceDto.setId(afterServiceDto.getId());
        afterServiceDto = distractionService.findById(beforeServiceDto.getId());
        softAssertions.assertThat(beforeServiceDto.getEmployee())
                .describedAs(String.format("Проверяем, что работник найденной по id сущности сущности %s", beforeServiceDto.getEmployee()))
                .isEqualTo(afterServiceDto.getEmployee());

        //Update test
        beforeServiceDto.setEmployee(employee2);
        afterServiceDto = distractionService.updateById(beforeServiceDto.getId(), beforeServiceDto);
        softAssertions.assertThat(beforeServiceDto.getEmployee())
                .describedAs(String.format("Проверяем, что работник обновленной сущности сущности %s", afterServiceDto.getEmployee()))
                .isEqualTo(afterServiceDto.getEmployee());

        //FindAll and Delete test
        distractionService.deleteById(beforeServiceDto.getId());
        softAssertions.assertThat(distractionService.findAll())
                .describedAs("Проверяем, что после удаления список пустой")
                .isEqualTo(Collections.EMPTY_LIST);
        softAssertions.assertAll();
    }
}
