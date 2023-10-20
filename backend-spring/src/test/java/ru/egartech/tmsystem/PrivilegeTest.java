package ru.egartech.tmsystem;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.egartech.tmsystem.model.dto.PrivilegeDto;
import ru.egartech.tmsystem.model.mapping.DepartmentMapper;
import ru.egartech.tmsystem.model.mapping.EmployeeMapper;
import ru.egartech.tmsystem.model.mapping.PositionMapper;
import ru.egartech.tmsystem.model.repository.EmployeeRepository;
import ru.egartech.tmsystem.service.PrivilegeService;
import ru.egartech.tmsystem.service.SettingsService;

import java.util.Collections;

@Slf4j
@SpringBootTest
public class PrivilegeTest implements CrudTest {
    @Autowired
    PrivilegeService privilegeService;
    @Autowired
    SettingsService settingsService;
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
        String name = "Увеличенное количество пропусков работы";
        Long increasedAmount = 5L;

        SoftAssertions softAssertions = new SoftAssertions();

        //Save test
        PrivilegeDto beforeServiceDto = privilegeService.save(new PrivilegeDto(name, increasedAmount));
        PrivilegeDto afterServiceDto = privilegeService.save(beforeServiceDto);
        softAssertions.assertThat(beforeServiceDto.getName())
                .describedAs(String.format("Проверяем, что имя наименование сущности %s", beforeServiceDto.getName()))
                .isEqualTo(afterServiceDto.getName());

        //FindById test
        beforeServiceDto.setId(afterServiceDto.getId());
        afterServiceDto = privilegeService.findById(beforeServiceDto.getId());
        softAssertions.assertThat(beforeServiceDto.getName())
                .describedAs(String.format("Проверяем, что наименование найденной по id сущности %s",
                        beforeServiceDto.getName()))
                .isEqualTo(afterServiceDto.getName());

        //Update test
        beforeServiceDto.setIncreasedAmount(10L);
        afterServiceDto = privilegeService.updateById(beforeServiceDto.getId(), beforeServiceDto);
        softAssertions.assertThat(beforeServiceDto.getIncreasedAmount())
                .describedAs(String.format("Проверяем, что увеличенное значение обновленной сущности сущности %s",
                        afterServiceDto.getIncreasedAmount()))
                .isEqualTo(afterServiceDto.getIncreasedAmount());

        //FindAll and Delete test
        privilegeService.deleteById(beforeServiceDto.getId());
        softAssertions.assertThat(privilegeService.findAll())
                .describedAs("Проверяем, что после удаления список пустой")
                .isEqualTo(Collections.EMPTY_LIST);


        softAssertions.assertAll();
    }

}
