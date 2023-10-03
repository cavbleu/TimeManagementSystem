package ru.egartech.tmsystem.domain.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.domain.rest.dto.RestDto;
import ru.egartech.tmsystem.domain.rest.entity.Rest;
import ru.egartech.tmsystem.domain.rest.mapper.RestMapper;
import ru.egartech.tmsystem.domain.rest.repository.RestRepository;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class RestServiceImpl implements RestService{

    private final RestRepository repository;
    private final RestMapper mapper;

    @Override
    public List<RestDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public Optional<RestDto> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public RestDto save(RestDto dto) {
        Rest rest = repository.save(mapper.toEntity(dto));
        return mapper.toDto(rest);
    }

    @Override
    public RestDto updateById(Long id, RestDto dto) {
        return repository.findById(id)
                .map(r -> mapper.toDto(repository.save(r)))
                .orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
