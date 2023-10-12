package ru.egartech.tmsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.exception.DepartmentConstraintException;
import ru.egartech.tmsystem.exception.DurationException;
import ru.egartech.tmsystem.exception.PositionNotFoundException;
import ru.egartech.tmsystem.exception.StartDateEarlierException;
import ru.egartech.tmsystem.model.dto.PositionDto;
import ru.egartech.tmsystem.model.dto.PositionSummaryDto;
import ru.egartech.tmsystem.model.dto.SettingsDto;
import ru.egartech.tmsystem.model.entity.Position;
import ru.egartech.tmsystem.model.mapping.PositionMapper;
import ru.egartech.tmsystem.model.repository.PositionRepository;
import ru.egartech.tmsystem.utils.SummaryFormatter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository repository;
    private final PositionMapper mapper;
    private final SettingsService settingsService;
    private final DepartmentService departmentService;

    @Override
    public List<PositionDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public PositionDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new PositionNotFoundException(id));
    }

    @Override
    public PositionDto save(PositionDto dto) {
        Position position = repository.save(mapper.toEntity(dto));
        return mapper.toDto(position);
    }

    @Override
    public PositionDto updateById(Long id, PositionDto dto) {
        return repository.findById(id)
                .map(entity -> {
                    BeanUtils.copyProperties(mapper.toEntity(dto), entity, "id");
                    return mapper.toDto(repository.save(entity));
                })
                .orElseThrow(() -> new PositionNotFoundException(id));
    }

    @Override
    public void deleteById(Long id) {
        if (!findById(id).getEmployees().isEmpty()) {
            throw new DepartmentConstraintException();
        } else {
            repository.deleteById(id);
        }
    }

    @Override
    public List<PositionSummaryDto> positionsSummaryByPeriod(LocalDateTime startDate, LocalDateTime endDate) {


        if (Duration.between(startDate, endDate).toDays() > 30) {
            throw new DurationException(30);
        }

        if (startDate.isAfter(endDate)) {
            throw new StartDateEarlierException();
        }

        List<PositionSummaryDto> positionsSummary = new ArrayList<>();
        List<PositionDto> positions = findAll();
        SettingsDto settings = settingsService.findByCurrentSettingsProfile();

        for (PositionDto position : positions) {

            PositionSummaryDto positionSummaryDto = new PositionSummaryDto();
            positionSummaryDto.setId(position.getId());
            long workTime = positionWorkTimeByPeriod(startDate.toLocalDate(), endDate.toLocalDate(), position.getId());
            long distractionTime = positionDistractionTimeByPeriod(startDate.toLocalDate(), endDate.toLocalDate(), position.getId());
            long restTime = positionRestTimeByPeriod(startDate.toLocalDate(), endDate.toLocalDate(), position.getId());
            SummaryFormatter.toSummaryDto(workTime, distractionTime, restTime,
                    positionSummaryDto, position, startDate.toLocalDate(), endDate.toLocalDate(), settings);
            positionSummaryDto.setDepartmentName(position.getDepartment().getName());
            positionSummaryDto.setPositionName(position.getName());
            positionsSummary.add(positionSummaryDto);
        }

        return positionsSummary;
    }

    @Override
    public long positionWorkTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id) {
        return repository.positionWorkTimeByPeriod(startDate, endDate, id)
                .orElse(0L);
    }

    @Override
    public long positionDistractionTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id) {
        return repository.positionDistractionTimeByPeriod(startDate, endDate, id)
                .orElse(0L);
    }

    @Override
    public long positionRestTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id) {
        return repository.positionRestTimeByPeriod(startDate, endDate, id)
                .orElse(0L);
    }

    @Override
    public PositionDto save(PositionDto positionDto, String departmentName) {
        positionDto.setDepartment(departmentService.findByName(departmentName));
        return save(positionDto);
    }

    @Override
    public PositionDto update(PositionDto positionDto, String departmentName, Long id) {
        positionDto.setDepartment(departmentService.findByName(departmentName));
        return updateById(id, positionDto);
    }

    @Override
    public Position findByName(String positionName) {
        return repository.findByName(positionName)
                .orElseThrow(() -> new PositionNotFoundException(positionName));
    }
}
