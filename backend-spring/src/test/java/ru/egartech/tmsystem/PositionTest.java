package ru.egartech.tmsystem;

import org.assertj.core.api.SoftAssertions;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.egartech.tmsystem.model.dto.DepartmentDto;
import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.dto.PositionDto;
import ru.egartech.tmsystem.model.entity.Department;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.model.entity.Position;
import ru.egartech.tmsystem.model.mapping.DepartmentMapper;
import ru.egartech.tmsystem.model.mapping.EmployeeMapper;
import ru.egartech.tmsystem.model.mapping.PositionMapper;
import ru.egartech.tmsystem.model.repository.EmployeeRepository;
import ru.egartech.tmsystem.service.*;

import java.util.Collections;
import java.util.List;

@DataJpaTest
//@Transactional
public class PositionTest implements BaseTest{

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

    @Override
    @Test
    public void crudTest() {
        Department department = departmentMapper.toEntity(departmentService.save(new DepartmentDto("IT")));
        Position position = positionMapper.toEntity(positionService.save(new PositionDto("QA", department)));


        Hibernate.initialize(position.getEmployees());
        List<PositionDto> positionList = positionService.findAll();

//        Employee employee = employeeMapper.toEntity(employeeService.save(new EmployeeDto("Petr", 29, position)));

        PositionDto beforeServiceDto = positionMapper.toDto(position);

        SoftAssertions softAssertions = new SoftAssertions();
        //Save test
        PositionDto afterServiceDto = positionService.save(beforeServiceDto);
        softAssertions.assertThat(beforeServiceDto.getName())
                .describedAs(String.format("Проверяем, что имя сохраненной сущности %s", beforeServiceDto.getName()))
                .isEqualTo(afterServiceDto.getName());
        beforeServiceDto.setId(afterServiceDto.getId());
        //FindById test
        beforeServiceDto = positionService.findById(beforeServiceDto.getId());
        softAssertions.assertThat(beforeServiceDto.getName())
                .describedAs(String.format("Проверяем, что имя найденной по id сущности сущности %s", beforeServiceDto.getName()))
                .isEqualTo(afterServiceDto.getName());
        //Update test
        beforeServiceDto.setName("TeamLead");
        afterServiceDto = positionService.updateById(beforeServiceDto.getId(), beforeServiceDto);
        softAssertions.assertThat(beforeServiceDto.getName())
                .describedAs(String.format("Проверяем, что имя обновленной сущности сущности %s", beforeServiceDto.getName()));
        //FindAll and Delete test
        positionService.deleteById(beforeServiceDto.getId());
        softAssertions.assertThat(positionService.findAll())
                .describedAs("Проверяем, что список пустой")
                .isEqualTo(Collections.EMPTY_LIST);
        softAssertions.assertAll();
    }
}
