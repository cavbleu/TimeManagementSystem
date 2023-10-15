package ru.egartech.tmsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.exception.RestNotFoundException;
import ru.egartech.tmsystem.model.dto.RestDto;
import ru.egartech.tmsystem.model.entity.Rest;
import ru.egartech.tmsystem.model.mapping.RestMapper;
import ru.egartech.tmsystem.model.mapping.TimeSheetMapper;
import ru.egartech.tmsystem.model.repository.RestRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestServiceImpl implements RestService {

    private final RestRepository repository;
    private final TimeSheetService timeSheetService;
    private final RestMapper mapper;
    private final TimeSheetMapper timeSheetMapper;

    @Override
    public List<RestDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }
    @Override
    public RestDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(RestNotFoundException::new);
    }

    @Override
    public RestDto save(RestDto dto) {
        Rest rest = repository.save(mapper.toEntity(dto));
        return mapper.toDto(rest);
    }

    @Override
    public RestDto updateById(Long id, RestDto dto) {
        return repository.findById(id)
                .map(entity -> {
                    BeanUtils.copyProperties(mapper.toEntity(dto), entity, "id");
                    return mapper.toDto(repository.save(entity));
                })
                .orElseThrow(RestNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public RestDto update(Long resId, RestDto restDto) {
        return updateById(resId, restDto);
    }

    @Override
    public RestDto save(Long timeSheetId, RestDto dto) {
        return save(dto);
    }
}
