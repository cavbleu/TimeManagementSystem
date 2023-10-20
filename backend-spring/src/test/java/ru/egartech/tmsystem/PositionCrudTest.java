package ru.egartech.tmsystem;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.egartech.tmsystem.model.dto.DepartmentDto;
import ru.egartech.tmsystem.model.dto.PositionDto;
import ru.egartech.tmsystem.model.entity.Department;
import ru.egartech.tmsystem.model.entity.Position;
import ru.egartech.tmsystem.model.mapping.DepartmentMapper;
import ru.egartech.tmsystem.model.mapping.EmployeeMapper;
import ru.egartech.tmsystem.model.mapping.PositionMapper;
import ru.egartech.tmsystem.model.repository.EmployeeRepository;
import ru.egartech.tmsystem.service.DepartmentService;
import ru.egartech.tmsystem.service.PositionService;

import java.util.Collections;

@SpringBootTest
public class PositionCrudTest implements CrudTest {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PositionService positionService;
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
        SoftAssertions softAssertions = new SoftAssertions();

        //Save test
        PositionDto beforeServiceDto = positionMapper.toDto(position);
        PositionDto afterServiceDto = positionService.save(beforeServiceDto);
        softAssertions.assertThat(beforeServiceDto.getName())
                .describedAs(String.format("Проверяем, что имя сохраненной сущности %s", beforeServiceDto.getName()))
                .isEqualTo(afterServiceDto.getName());

        //FindById test
        beforeServiceDto.setId(afterServiceDto.getId());
        afterServiceDto = positionService.findById(beforeServiceDto.getId());
        softAssertions.assertThat(beforeServiceDto.getName())
                .describedAs(String.format("Проверяем, что имя найденной по id сущности %s", beforeServiceDto.getName()))
                .isEqualTo(afterServiceDto.getName());

        //Update test
        beforeServiceDto.setName("TeamLead");
        afterServiceDto = positionService.updateById(beforeServiceDto.getId(), beforeServiceDto);
        softAssertions.assertThat(beforeServiceDto.getName())
                .describedAs(String.format("Проверяем, что имя обновленной сущности %s", beforeServiceDto.getName()))
                .isEqualTo(afterServiceDto.getName());

        //FindAll and Delete test
        positionService.deleteById(beforeServiceDto.getId());
        softAssertions.assertThat(positionService.findAll())
                .describedAs("Проверяем, что список пустой")
                .isEqualTo(Collections.EMPTY_LIST);
        softAssertions.assertAll();
    }
}