package ru.egartech.tmsystem;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.egartech.tmsystem.exception.ActiveProfileDeleteException;
import ru.egartech.tmsystem.exception.ActiveProfileNotInstalledException;
import ru.egartech.tmsystem.model.dto.SettingsDto;
import ru.egartech.tmsystem.model.mapping.DepartmentMapper;
import ru.egartech.tmsystem.model.mapping.EmployeeMapper;
import ru.egartech.tmsystem.model.mapping.PositionMapper;
import ru.egartech.tmsystem.model.repository.EmployeeRepository;
import ru.egartech.tmsystem.service.SettingsService;

import java.time.LocalTime;

@Slf4j
@SpringBootTest
@DirtiesContext
public class SettingsTest implements CrudTest {

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
        String name = "Стандартный профиль";
        boolean currentSettingsProfile = false;
        long defaultWorkTime = 540;
        LocalTime defaultStartWork = LocalTime.of(9, 0);
        long maxLateCountByMonth = 1;
        long maxEarlyLivingCountByMonth = 2;
        long maxAbsenceCountByMonth = 3;
        long maxSkipCountByMonth = 4;
        long maxDistractionTimeByDay = 15;
        long maxRestTimeByDay = 35;
        long maxExcessDistractionCountByMonth = 2;
        long maxExcessRestCountByMonth = 2;

        SettingsDto dtoForTest = settingsService.save(new SettingsDto("Альтернативный профиль", true, defaultWorkTime, defaultStartWork, maxLateCountByMonth,
                maxEarlyLivingCountByMonth, maxAbsenceCountByMonth, maxSkipCountByMonth, maxDistractionTimeByDay, maxRestTimeByDay,
                maxExcessDistractionCountByMonth, maxExcessRestCountByMonth));
        SoftAssertions softAssertions = new SoftAssertions();

        //Save test
        SettingsDto beforeServiceDto = new SettingsDto(name, currentSettingsProfile, defaultWorkTime, defaultStartWork, maxLateCountByMonth,
                maxEarlyLivingCountByMonth, maxAbsenceCountByMonth, maxSkipCountByMonth, maxDistractionTimeByDay, maxRestTimeByDay,
                maxExcessDistractionCountByMonth, maxExcessRestCountByMonth);
        SettingsDto afterServiceDto = settingsService.save(beforeServiceDto);
        softAssertions.assertThat(beforeServiceDto.getName())
                .describedAs(String.format("Проверяем, что имя сохраненной сущности %s", beforeServiceDto.getName()))
                .isEqualTo(afterServiceDto.getName());

        //FindById test
        beforeServiceDto.setId(afterServiceDto.getId());
        afterServiceDto = settingsService.findById(beforeServiceDto.getId());
        softAssertions.assertThat(beforeServiceDto.getDefaultWorkTime())
                .describedAs(String.format("Проверяем, что норма рабочего времени найденной по id сущности %s",
                        beforeServiceDto.getDefaultWorkTime()))
                .isEqualTo(afterServiceDto.getDefaultWorkTime());

        //Update test
        beforeServiceDto.setMaxRestTimeByDay(10);
        afterServiceDto = settingsService.updateById(beforeServiceDto.getId(), beforeServiceDto);
        softAssertions.assertThat(beforeServiceDto.getMaxRestTimeByDay())
                .describedAs(String.format("Проверяем, что максимальное время перерывов обновленной сущности сущности %s",
                        afterServiceDto.getMaxRestTimeByDay()))
                .isEqualTo(afterServiceDto.getMaxRestTimeByDay());

        //CurrentProfile test
        beforeServiceDto.setCurrentSettingsProfile(true);
        settingsService.updateById(beforeServiceDto.getId(), beforeServiceDto);
        try {
            settingsService.deleteById(beforeServiceDto.getId());
            throw new ActiveProfileDeleteException();
        } catch (ActiveProfileDeleteException ex) {
            log.warn("Отработка валидации");
        }

        //FindAll and Delete test
        beforeServiceDto.setCurrentSettingsProfile(false);

        try {
            settingsService.updateById(beforeServiceDto.getId(), beforeServiceDto);
            throw new ActiveProfileDeleteException();
        } catch (ActiveProfileNotInstalledException ex) {
            log.warn("Отработка валидации");
        }

        dtoForTest.setCurrentSettingsProfile(true);
        settingsService.updateById(dtoForTest.getId(), dtoForTest);
        settingsService.updateById(beforeServiceDto.getId(), beforeServiceDto);

        settingsService.deleteById(beforeServiceDto.getId());
        softAssertions.assertThat(settingsService.findAll().size())
                .describedAs("Проверяем, что в списке остался только один профиль")
                .isEqualTo(1);


        softAssertions.assertAll();
    }

}
