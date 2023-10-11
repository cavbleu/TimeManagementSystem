package ru.egartech.tmsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.exception.CurrentSettingsNotFoundException;
import ru.egartech.tmsystem.exception.SettingsNotFoundException;
import ru.egartech.tmsystem.exception.SettingsOneProfileException;
import ru.egartech.tmsystem.model.dto.SettingsDto;
import ru.egartech.tmsystem.model.entity.Settings;
import ru.egartech.tmsystem.model.mapping.SettingsMapper;
import ru.egartech.tmsystem.model.repository.SettingsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SettingsServiceImpl implements SettingsService {

    private final SettingsRepository repository;
    private final SettingsMapper mapper;

    @Override
    public List<SettingsDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public Optional<SettingsDto> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public SettingsDto save(SettingsDto dto) {
        if (dto.isCurrentSettingsProfile()) {
            for (SettingsDto settingsDto : findAll()) {
                settingsDto.setCurrentSettingsProfile(false);
                repository.save(mapper.toEntity(settingsDto));
            }
        }

        Settings settings = repository.save(mapper.toEntity(dto));
        return mapper.toDto(settings);
    }

    @Override
    public SettingsDto updateById(Long id, SettingsDto dto) {
        if (dto.isCurrentSettingsProfile()) {
            for (SettingsDto settingsDto : findAll()) {
                settingsDto.setCurrentSettingsProfile(false);
                repository.save(mapper.toEntity(settingsDto));
            }
        }

        return repository.findById(id)
                .map(entity -> {
                    BeanUtils.copyProperties(mapper.toEntity(dto), entity, "id");
                    return mapper.toDto(repository.save(entity));
                })
                .orElseThrow(() -> new SettingsNotFoundException(id));
    }

    @Override
    public void deleteById(Long id) {
        if (findAll().size() == 1) {
            throw new SettingsOneProfileException();
        } else if (findById(id).orElseThrow(() -> new SettingsNotFoundException(id)).isCurrentSettingsProfile()) {
            throw new CurrentSettingsNotFoundException();
        } else {
            repository.deleteById(id);
        }
    }

    @Override
    public SettingsDto findByCurrentSettingsProfile() {
        return repository.findByCurrentSettingsProfileIsTrue()
                .map(mapper::toDto)
                .orElseThrow(CurrentSettingsNotFoundException::new);
    }
}
