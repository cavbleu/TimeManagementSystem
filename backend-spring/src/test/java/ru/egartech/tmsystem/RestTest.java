package ru.egartech.tmsystem;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.dto.RestDto;
import ru.egartech.tmsystem.model.mapping.DepartmentMapper;
import ru.egartech.tmsystem.model.mapping.EmployeeMapper;
import ru.egartech.tmsystem.model.mapping.PositionMapper;
import ru.egartech.tmsystem.model.repository.EmployeeRepository;
import ru.egartech.tmsystem.service.EmployeeService;
import ru.egartech.tmsystem.service.RestService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;

@SpringBootTest
@DirtiesContext
public class RestTest implements CrudTest {

    @Autowired
    private RestService restService;
    @Autowired
    private EmployeeService employeeService;
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

        LocalTime startRest = LocalTime.of(9, 15);
        LocalTime endRest = LocalTime.of(9, 30);

        EmployeeDto employee1 = employeeService.save(new EmployeeDto("Petr", 29));
        EmployeeDto employee2 = employeeService.save(new EmployeeDto("Ivan", 32));
        SoftAssertions softAssertions = new SoftAssertions();

        //Save test
        RestDto beforeServiceDto = new RestDto(date, startRest, endRest, employee1);
        RestDto afterServiceDto = restService.save(beforeServiceDto);
        softAssertions.assertThat(beforeServiceDto.getEmployee())
                .describedAs(String.format("Проверяем, что соответствующий перерыву работник %s", beforeServiceDto.getEmployee()))
                .isEqualTo(afterServiceDto.getEmployee());

        //FindById test
        beforeServiceDto.setId(afterServiceDto.getId());
        afterServiceDto = restService.findById(beforeServiceDto.getId());
        softAssertions.assertThat(beforeServiceDto.getEmployee())
                .describedAs(String.format("Проверяем, что работник найденной по id сущности сущности %s", beforeServiceDto.getEmployee()))
                .isEqualTo(afterServiceDto.getEmployee());

        //Update test
        beforeServiceDto.setEmployee(employee2);
        afterServiceDto = restService.updateById(beforeServiceDto.getId(), beforeServiceDto);
        softAssertions.assertThat(beforeServiceDto.getEmployee())
                .describedAs(String.format("Проверяем, что работник обновленной сущности сущности %s", afterServiceDto.getEmployee()))
                .isEqualTo(afterServiceDto.getEmployee());

        //FindAll and Delete test
        restService.deleteById(beforeServiceDto.getId());
        softAssertions.assertThat(restService.findAll())
                .describedAs("Проверяем, что список пустой")
                .isEqualTo(Collections.EMPTY_LIST);
        softAssertions.assertAll();
    }
}
