package ru.egartech.tmsystem.domain.department.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.domain.department.dto.DepartmentDto;
import ru.egartech.tmsystem.domain.department.dto.DepartmentSummaryDto;
import ru.egartech.tmsystem.domain.department.entity.Department;
import ru.egartech.tmsystem.domain.department.mapper.DepartmentMapper;
import ru.egartech.tmsystem.domain.department.repository.DepartmentRepository;
import ru.egartech.tmsystem.domain.distraction.entity.Distraction;
import ru.egartech.tmsystem.domain.filter.dto.FilterDto;
import ru.egartech.tmsystem.domain.limit.entitiy.Limit;
import ru.egartech.tmsystem.domain.rest.entity.Rest;
import ru.egartech.tmsystem.domain.timesheet.entity.TimeSheet;
import ru.egartech.tmsystem.formatter.StatisticTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;
    private final DepartmentMapper departmentMapper;
    private final StatisticTimeFormatter formatter;

    @Override
    public List<DepartmentSummaryDto> departmentsSummary(FilterDto filter, List<TimeSheet> timeSheets,
                                                         List<Rest> rests, List<Distraction> distractions, Limit limit) {
        List<DepartmentSummaryDto> departmentsSummary = new ArrayList<>();
        List<DepartmentDto> departments = findAll();
        for (DepartmentDto department : departments) {

            DepartmentSummaryDto departmentSummaryDto = new DepartmentSummaryDto();

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

            departmentSummaryDto.setName(department.getName());
            departmentSummaryDto.setWorkTime(formatter.format(workTime, workTimePercent));
            departmentSummaryDto.setProductiveTime(formatter.format(productiveTime, productiveTimePercent));
            departmentSummaryDto.setDistractionTime(formatter.format(distractionTime, distractionTimePercent));
            departmentSummaryDto.setRestTime(formatter.format(restTime, restTimePercent));
            departmentSummaryDto.setOverTime(formatter.format(overTime, overTimeMinutes, overTimePercent));

            departmentsSummary.add(departmentSummaryDto);
        }

        return departmentsSummary;
    }

    @Override
    public List<DepartmentDto> findAll() {
        return repository.findAll().stream()
                .map(departmentMapper::toDto)
                .toList();
    }

    @Override
    public Optional<DepartmentDto> findById(Long id) {
        return repository.findById(id)
                .map(departmentMapper::toDto);
    }

    @Override
    public DepartmentDto save(DepartmentDto dto) {
        Department department = repository.save(departmentMapper.toEntity(dto));
        return departmentMapper.toDto(department);
    }

    @Override
    public DepartmentDto updateById(Long aLong, DepartmentDto dto) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
