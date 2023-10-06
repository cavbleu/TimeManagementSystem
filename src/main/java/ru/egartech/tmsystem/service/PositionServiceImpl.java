package ru.egartech.tmsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.model.dto.PositionDto;
import ru.egartech.tmsystem.model.dto.PositionSummaryDto;
import ru.egartech.tmsystem.model.dto.SettingsDto;
import ru.egartech.tmsystem.model.entity.Position;
import ru.egartech.tmsystem.model.mapping.PositionMapper;
import ru.egartech.tmsystem.model.repository.PositionRepository;
import ru.egartech.tmsystem.utils.SummaryFormatter;

import java.time.LocalDate;
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
    public List<PositionSummaryDto> positionsSummaryByPeriod(LocalDate startDate, LocalDate endDate) {

        List<PositionSummaryDto> positionsSummary = new ArrayList<>();
        List<PositionDto> positions = findAll();
        SettingsDto settings = settingsService.findByCurrentSettingsProfile();

        for (PositionDto position : positions) {

            PositionSummaryDto positionSummaryDto = new PositionSummaryDto();
            long workTime = positionWorkTimeByPeriod(startDate, endDate, position.getId());
            long distractionTime = positionDistractionTimeByPeriod(startDate, endDate, position.getId());
            long restTime = positionRestTimeByPeriod(startDate, endDate, position.getId());
            SummaryFormatter.toSummaryDto(workTime, distractionTime, restTime,
                    positionSummaryDto, position, startDate, endDate, settings);
            positionSummaryDto.setDepartmentName(position.getDepartment().getName());
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
}
