package ru.egartech.tmsystem.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.model.dto.DistractionDto;
import ru.egartech.tmsystem.model.dto.RestDto;
import ru.egartech.tmsystem.model.entity.Distraction;
import ru.egartech.tmsystem.model.mapping.DistractionMapper;
import ru.egartech.tmsystem.model.mapping.TimeSheetMapper;
import ru.egartech.tmsystem.model.repository.DistractionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DistractionServiceImpl implements DistractionService {

    private final DistractionRepository repository;
    private final DistractionMapper mapper;
    private final TimeSheetMapper timeSheetMapper;
    private final TimeSheetService timeSheetService;

    @Override
    public List<DistractionDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public Optional<DistractionDto> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public DistractionDto save(DistractionDto dto) {
        Distraction distraction = repository.save(mapper.toEntity(dto));
        return mapper.toDto(distraction);
    }

    @Override
    public DistractionDto updateById(Long id, DistractionDto dto) {
        return repository.findById(id)
                .map(entity -> {
                    BeanUtils.copyProperties(mapper.toEntity(dto), entity, "id");
                    return mapper.toDto(repository.save(entity));
                })
                .orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public DistractionDto save(Long timeSheetId, DistractionDto distractionDto) {
        distractionDto.setTimeSheet(timeSheetMapper.toEntity(timeSheetService.findById(timeSheetId).orElseThrow()));
        return save(distractionDto);
    }

    @Override
    public DistractionDto update(Long timeSheetId, Long restId, DistractionDto dto) {
        dto.setTimeSheet(timeSheetMapper.toEntity(timeSheetService.findById(timeSheetId)
                .orElseThrow()));
        return updateById(restId, dto);
    }
}
