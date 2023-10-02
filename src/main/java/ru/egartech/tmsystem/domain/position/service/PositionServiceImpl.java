package ru.egartech.tmsystem.domain.position.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.domain.department.dto.DepartmentDto;
import ru.egartech.tmsystem.domain.department.dto.DepartmentSummaryDto;
import ru.egartech.tmsystem.domain.department.mapper.DepartmentMapper;
import ru.egartech.tmsystem.domain.department.service.DepartmentService;
import ru.egartech.tmsystem.domain.distraction.dto.DistractionDto;
import ru.egartech.tmsystem.domain.distraction.entity.Distraction;
import ru.egartech.tmsystem.domain.distraction.service.DistractionService;
import ru.egartech.tmsystem.domain.filter.dto.FilterDto;
import ru.egartech.tmsystem.domain.position.dto.PositionDto;
import ru.egartech.tmsystem.domain.position.dto.PositionSummaryDto;
import ru.egartech.tmsystem.domain.position.entity.Position;
import ru.egartech.tmsystem.domain.position.mapper.PositionMapper;
import ru.egartech.tmsystem.domain.position.repository.PositionRepository;
import ru.egartech.tmsystem.domain.rest.RestService;
import ru.egartech.tmsystem.domain.rest.dto.RestDto;
import ru.egartech.tmsystem.domain.rest.entity.Rest;
import ru.egartech.tmsystem.domain.settings.dto.SettingsDto;
import ru.egartech.tmsystem.domain.settings.entitiy.Settings;
import ru.egartech.tmsystem.domain.settings.service.SettingsService;
import ru.egartech.tmsystem.domain.timesheet.dto.TimeSheetDto;
import ru.egartech.tmsystem.domain.timesheet.entity.TimeSheet;
import ru.egartech.tmsystem.domain.timesheet.service.TimeSheetService;
import ru.egartech.tmsystem.formatter.StatisticTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository repository;
    private final PositionMapper mapper;
    private final DepartmentService departmentService;
    private final TimeSheetService timeSheetService;
    private final DistractionService distractionService;
    private final SettingsService settingsService;
    private final RestService restService;
    private final StatisticTimeFormatter formatter;

    @Override
    public List<PositionDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public Optional<PositionDto> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public PositionDto save(PositionDto dto) {
        Position position = repository.save(mapper.toEntity(dto));
        return mapper.toDto(position);
    }

    @Override
    public PositionDto updateById(Long id, PositionDto dto) {
        return repository.findById(id)
                .map(p -> mapper.toDto(repository.save(p)))
                .orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<PositionSummaryDto> positionsSummary(FilterDto filter) {

        List<PositionSummaryDto> positionSummary = new ArrayList<>();
        List<PositionDto> positions = findAll();
        List<DepartmentDto> departments = departmentService.findAll();
        SettingsDto settings = settingsService.findByCurrentSettingsProfile();

        List<TimeSheetDto> timeSheets = timeSheetService.findAll();
        List<DistractionDto> distractions = distractionService.findAll();
        List<RestDto> rests = restService.findAll();

        for (PositionDto position : positions) {

            PositionSummaryDto positionSummaryDto = new PositionSummaryDto();

            long workTime = 0;
            long productiveTime = 0;
            int days = 0;
            for (TimeSheetDto timeSheet : timeSheets) {
                workTime += timeSheet.getWorkTime();
                productiveTime += timeSheet.getProductiveTime();
                days++;
            }
            long distractionTime = 0;
            for (DistractionDto distraction : distractions) {
                distractionTime += distraction.getDistractionTime();
            }
            long restTime = 0;
            for (RestDto rest : rests) {
                restTime += rest.getRestTime();
            }
            long overTime = workTime - settings.getDefaultWorkTime() * days;
            long overTimeMinutes = overTime > 0 ? overTime : -overTime;


            double workTimePercent = (double) (workTime * 100) / settings.getDefaultWorkTime();
            double productiveTimePercent = (double) (productiveTime * 100) / workTime;
            double distractionTimePercent = (double) (distractionTime * 100) / workTime;
            double restTimePercent = (double) (restTime * 100) / workTime;
            double overTimePercent = (double) (workTime * 100) / settings.getDefaultWorkTime() * days;

//            departmentSummaryDto.setName(department.getName());
//            departmentSummaryDto.setWorkTime(formatter.format(workTime, workTimePercent));
//            departmentSummaryDto.setProductiveTime(formatter.format(productiveTime, productiveTimePercent));
//            departmentSummaryDto.setDistractionTime(formatter.format(distractionTime, distractionTimePercent));
//            departmentSummaryDto.setRestTime(formatter.format(restTime, restTimePercent));
//            departmentSummaryDto.setOverTime(formatter.format(overTime, overTimeMinutes, overTimePercent));

            positionSummary.add(positionSummaryDto);
        }

        return positionSummary;
    }
}
