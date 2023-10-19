package ru.egartech.tmsystem;

import org.assertj.core.api.SoftAssertions;
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
import ru.egartech.tmsystem.model.mapping.DepartmentMapper;
import ru.egartech.tmsystem.model.mapping.EmployeeMapper;
import ru.egartech.tmsystem.model.mapping.PositionMapper;
import ru.egartech.tmsystem.model.repository.EmployeeRepository;
import ru.egartech.tmsystem.service.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;

@SpringBootTest
public class TimeSheetTest implements BaseTest {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PositionService positionService;
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

    @Override
    @Test
    public void crudTest() {
        LocalDate date = LocalDate.of(2023, 10, 19);

        LocalTime startWork = LocalTime.of(9, 0);
        LocalTime endWork = LocalTime.of(18, 0);

        Department department = departmentMapper.toEntity(departmentService.save(new DepartmentDto("IT")));
        Position position = positionMapper.toEntity(positionService.save(new PositionDto("QA", department)));
        Employee employee1 = employeeMapper.toEntity(employeeService.save(new EmployeeDto("Petr", 29, position)));
        Employee employee2 = employeeMapper.toEntity(employeeService.save(new EmployeeDto("Ivan", 32, position)));
        SoftAssertions softAssertions = new SoftAssertions();

        //Save test
        TimeSheetDto beforeServiceDto = new TimeSheetDto(date, startWork, endWork, employee1);
        TimeSheetDto afterServiceDto = timeSheetService.save(beforeServiceDto);
        softAssertions.assertThat(beforeServiceDto.getEmployee())
                .describedAs(String.format("Проверяем, что соответствующий таймшиту работник %s", beforeServiceDto.getEmployee()))
                .isEqualTo(afterServiceDto.getEmployee());

        //FindById test
        beforeServiceDto.setId(afterServiceDto.getId());
        afterServiceDto = timeSheetService.findById(beforeServiceDto.getId());
        softAssertions.assertThat(beforeServiceDto.getEmployee())
                .describedAs(String.format("Проверяем, что работник найденной по id сущности %s", beforeServiceDto.getEmployee()))
                .isEqualTo(afterServiceDto.getEmployee());

        //Update test
        beforeServiceDto.setEmployee(employee2);
        afterServiceDto = timeSheetService.updateById(beforeServiceDto.getId(), beforeServiceDto);
        softAssertions.assertThat(beforeServiceDto.getEmployee())
                .describedAs(String.format("Проверяем, что работник обновленной сущности %s", afterServiceDto.getEmployee()))
                .isEqualTo(afterServiceDto.getEmployee());

        //FindAll and Delete test
        timeSheetService.deleteById(beforeServiceDto.getId());
        softAssertions.assertThat(timeSheetService.findAll())
                .describedAs("Проверяем, что список пустой")
                .isEqualTo(Collections.EMPTY_LIST);
        softAssertions.assertAll();
    }
}
