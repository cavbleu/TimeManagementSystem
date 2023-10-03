package ru.egartech.tmsystem.domain.position.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.domain.filter.dto.FilterDto;
import ru.egartech.tmsystem.domain.position.dto.PositionDto;
import ru.egartech.tmsystem.domain.position.dto.PositionSummaryDto;
import ru.egartech.tmsystem.domain.position.entity.Position;
import ru.egartech.tmsystem.domain.position.mapper.PositionMapper;
import ru.egartech.tmsystem.domain.position.repository.PositionRepository;
import ru.egartech.tmsystem.domain.settings.dto.SettingsDto;
import ru.egartech.tmsystem.domain.settings.service.SettingsService;
import ru.egartech.tmsystem.utils.SummaryFormatter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository repository;
    private final PositionMapper mapper;
    private final SettingsService settingsService;

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

        List<PositionSummaryDto> positionsSummary = new ArrayList<>();
        List<PositionDto> positions = findAll();
        SettingsDto settings = settingsService.findByCurrentSettingsProfile();

        for (PositionDto position : positions) {

            PositionSummaryDto positionSummaryDto = new PositionSummaryDto();
            long workTime = positionWorkTimeByPeriod(filter, position.getName());
            long distractionTime = positionDistractionTimeByPeriod(filter, position.getName());
            long restTime = positionRestTimeByPeriod(filter, position.getName());
            long lunchTime = positionLunchTimeByPeriod(filter, position.getName());
            long productiveTime = workTime - distractionTime - restTime - lunchTime;
            long days = Duration.between(filter.getStartPeriod(), filter.getEndPeriod()).toDays();
            long overTime = workTime - settings.getDefaultWorkTime() * days;
            long overTimeMinutes = overTime > 0 ? overTime : -overTime;

            double workTimePercent = SummaryFormatter.percentFormat(workTime, settings.getDefaultWorkTime() * days);
            double productiveTimePercent = SummaryFormatter.percentFormat(productiveTime, workTime);
            double distractionTimePercent = SummaryFormatter.percentFormat(distractionTime, workTime);
            double restTimePercent = SummaryFormatter.percentFormat(restTime, workTime);
            double lunchTimePercent = SummaryFormatter.percentFormat(lunchTime, workTime);
            double overTimePercent = SummaryFormatter.percentFormat(overTime, settings.getDefaultWorkTime() * days);
            overTimePercent = overTimePercent > 0 ? overTimePercent : -overTimePercent;

            positionSummaryDto.setId(position.getId());
            positionSummaryDto.setName(position.getName());
            positionSummaryDto.setWorkTime(SummaryFormatter.statisticFormat(workTime, workTimePercent));
            positionSummaryDto.setProductiveTime(SummaryFormatter.statisticFormat(productiveTime, productiveTimePercent));
            positionSummaryDto.setDistractionTime(SummaryFormatter.statisticFormat(distractionTime, distractionTimePercent));
            positionSummaryDto.setRestTime(SummaryFormatter.statisticFormat(restTime, restTimePercent));
            positionSummaryDto.setLunchTime(SummaryFormatter.statisticFormat(lunchTime, lunchTimePercent));
            positionSummaryDto.setOverTime(SummaryFormatter.statisticFormat(overTime, overTimeMinutes, overTimePercent));

            positionsSummary.add(positionSummaryDto);
        }

        return positionsSummary;
    }

    @Override
    public long positionWorkTimeByPeriod(FilterDto filter, String positionName) {
        return repository.positionWorkTimeByPeriod(filter.getStartPeriod().toLocalDate(),
                filter.getEndPeriod().toLocalDate(), positionName);
    }

    @Override
    public long positionDistractionTimeByPeriod(FilterDto filter, String positionName) {
        return repository.positionDistractionTimeByPeriod(filter.getStartPeriod().toLocalDate(),
                filter.getEndPeriod().toLocalDate(), positionName);
    }

    @Override
    public long positionRestTimeByPeriod(FilterDto filter, String positionName) {
        return repository.positionRestTimeByPeriod(filter.getStartPeriod().toLocalDate(),
                filter.getEndPeriod().toLocalDate(), positionName);
    }

    @Override
    public long positionLunchTimeByPeriod(FilterDto filter, String positionName) {
        return repository.positionLunchTimeByPeriod(filter.getStartPeriod().toLocalDate(),
                filter.getEndPeriod().toLocalDate(), positionName);
    }
}
