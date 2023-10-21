package ru.egartech.tmsystem;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.egartech.tmsystem.model.dto.DistractionDto;
import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.mapping.DepartmentMapper;
import ru.egartech.tmsystem.model.mapping.EmployeeMapper;
import ru.egartech.tmsystem.model.mapping.PositionMapper;
import ru.egartech.tmsystem.model.repository.EmployeeRepository;
import ru.egartech.tmsystem.service.DistractionService;
import ru.egartech.tmsystem.service.EmployeeService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;

@SpringBootTest
@DirtiesContext
public class DistractionTest implements CrudTest {

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

        EmployeeDto employee1 = employeeService.save(new EmployeeDto("Petr", 29));
        EmployeeDto employee2 = employeeService.save(new EmployeeDto("Ivan", 32));
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
