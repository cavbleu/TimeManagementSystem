package ru.egartech.tmsystem.domain.department.mapper;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.domain.department.dto.DepartmentDto;
import ru.egartech.tmsystem.domain.department.entity.Department;
import ru.egartech.tmsystem.domain.distraction.entity.Distraction;
import ru.egartech.tmsystem.domain.filter.dto.FilterDto;
import ru.egartech.tmsystem.domain.limit.entitiy.Limit;
import ru.egartech.tmsystem.domain.rest.entity.Rest;
import ru.egartech.tmsystem.domain.timesheet.entity.TimeSheet;

import java.time.Duration;
import java.util.List;

@Mapper
public abstract class DepartmentMapper {
    public DepartmentDto toDepartmentDto(FilterDto filter, Department department, List<TimeSheet> timeSheets,
                                         List<Rest> rests, List<Distraction> distractions, Limit limit) {
        DepartmentDto departmentDTO = new DepartmentDto();

        long workTime = 0;
        int days = 0;
        for (TimeSheet timeSheet : timeSheets) {
            workTime += timeSheet.getWorkTime();
            days++;
        }
        long productiveTime = 0;
        for (TimeSheet timeSheet : timeSheets) {
            productiveTime += timeSheet.getProductiveTime();
        }
        long distractionTime = 0;
        for (Distraction distraction : distractions) {
            distractionTime += distraction.getDistractionTime();
        }
        long restTime = 0;
        for (Rest rest : rests) {
            restTime += rest.getRestTime();
        }
        long overTime = workTime - limit.getDefaultWorkTime() * days;
        long overTimeMinutes = overTime > 0 ? overTime : -overTime;


        double workTimePercent = (double) (workTime * 100) / limit.getDefaultWorkTime();
        double productiveTimePercent = (double) (productiveTime * 100) / workTime;
        double distractionTimePercent = (double) (distractionTime * 100) / workTime;
        double restTimePercent = (double) (restTime * 100) / workTime;
        double overTimePercent = (double) (workTime * 100) / limit.getDefaultWorkTime() * days;

        departmentDTO.setId(department.getId());
        departmentDTO.setName(department.getName());
        departmentDTO.setWorkTime(String
                .format("%d ч %02d мин (%.1f ",
                        Duration.ofMinutes(workTime).toHoursPart(),
                        Duration.ofMinutes(workTime).toMinutesPart(),
                        workTimePercent)
                .concat("%)"));
        departmentDTO.setProductiveTime(String
                .format("%d ч %02d мин (%.1f ",
                        Duration.ofMinutes(productiveTime).toHoursPart(),
                        Duration.ofMinutes(productiveTime).toMinutesPart(),
                        productiveTimePercent)
                .concat("%)"));
        departmentDTO.setDistractionTime(String
                .format("%d ч %02d мин (%.1f ",
                        Duration.ofMinutes(distractionTime).toHoursPart(),
                        Duration.ofMinutes(distractionTime).toMinutesPart(),
                        distractionTimePercent)
                .concat("%)"));
        departmentDTO.setRestTime(String
                .format("%d ч %02d мин (%.1f ",
                        Duration.ofMinutes(restTime).toHoursPart(),
                        Duration.ofMinutes(restTime).toMinutesPart(),
                        restTimePercent)
                .concat("%)"));
        departmentDTO.setOverTime(String
                .format("%d ч %02d мин (%.1f ",
                        Duration.ofMinutes(overTime).toHoursPart(),
                        Duration.ofMinutes(overTimeMinutes).toMinutesPart(),
                        overTimePercent)
                .concat("%)"));

        return departmentDTO;
    }

    public Department toDepartment(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setName(departmentDto.getName());
        return department;
    }
}