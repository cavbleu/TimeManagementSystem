package ru.egartech.tmsystem;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.egartech.tmsystem.model.dto.DepartmentDto;
import ru.egartech.tmsystem.model.entity.Department;
import ru.egartech.tmsystem.model.repository.DepartmentRepository;
import ru.egartech.tmsystem.service.DepartmentService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DepartmentTest {

    @Autowired
    private DepartmentService departmentService;

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
}