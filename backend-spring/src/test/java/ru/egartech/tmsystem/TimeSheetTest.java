package ru.egartech.tmsystem;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.dto.TimeSheetDto;
import ru.egartech.tmsystem.model.mapping.DepartmentMapper;
import ru.egartech.tmsystem.model.mapping.EmployeeMapper;
import ru.egartech.tmsystem.model.mapping.PositionMapper;
import ru.egartech.tmsystem.model.repository.EmployeeRepository;
import ru.egartech.tmsystem.service.EmployeeService;
import ru.egartech.tmsystem.service.TimeSheetService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TimeSheetTest implements CrudTest {

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

        EmployeeDto employee1 = employeeService.save(new EmployeeDto("Petr", 29));
        EmployeeDto employee2 = employeeService.save(new EmployeeDto("Ivan", 32));
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
