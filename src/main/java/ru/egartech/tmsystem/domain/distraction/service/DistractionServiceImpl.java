package ru.egartech.tmsystem.domain.distraction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.domain.distraction.dto.DistractionDto;
import ru.egartech.tmsystem.domain.distraction.entity.Distraction;
import ru.egartech.tmsystem.domain.distraction.mapper.DistractionMapper;
import ru.egartech.tmsystem.domain.distraction.repository.DistractionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DistractionServiceImpl implements DistractionService {

    private final DistractionRepository repository;
    private final DistractionMapper mapper;

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
                .map(d -> mapper.toDto(repository.save(d)))
                .orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
