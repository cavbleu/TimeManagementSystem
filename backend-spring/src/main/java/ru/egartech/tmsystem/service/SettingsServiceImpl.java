package ru.egartech.tmsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.exception.ActiveProfileDeleteException;
import ru.egartech.tmsystem.exception.ActiveProfileNotInstalledException;
import ru.egartech.tmsystem.exception.CustomEntityNotFoundException;
import ru.egartech.tmsystem.exception.SettingsOneProfileException;
import ru.egartech.tmsystem.model.dto.SettingsDto;
import ru.egartech.tmsystem.model.entity.Settings;
import ru.egartech.tmsystem.model.mapping.SettingsMapper;
import ru.egartech.tmsystem.model.repository.SettingsRepository;

import java.util.List;

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
    public SettingsDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new CustomEntityNotFoundException(id));
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
        List<SettingsDto> list = findAll();
        if (dto.isCurrentSettingsProfile()) {
            for (SettingsDto settingsDto : list) {
                settingsDto.setCurrentSettingsProfile(false);
                repository.save(mapper.toEntity(settingsDto));
            }
        } else {
            if (!findById(id).isCurrentSettingsProfile()) {
                return repository.findById(id)
                        .map(entity -> {
                            BeanUtils.copyProperties(mapper.toEntity(dto), entity, "id");
                            return mapper.toDto(repository.save(entity));
                        })
                        .orElseThrow(() -> new CustomEntityNotFoundException(id));
            } else {
                throw new ActiveProfileNotInstalledException();
            }
        }
        return repository.findById(id)
                .map(entity -> {
                    BeanUtils.copyProperties(mapper.toEntity(dto), entity, "id");
                    return mapper.toDto(repository.save(entity));
                })
                .orElseThrow(() -> new CustomEntityNotFoundException(id));
    }

    @Override
    public void deleteById(Long id) {
        if (findAll().size() == 1) {
            throw new SettingsOneProfileException();
        } else if (findById(id).isCurrentSettingsProfile()) {
            throw new ActiveProfileDeleteException();
        } else {
            repository.deleteById(id);
        }
    }

    @Override
    public SettingsDto findByCurrentSettingsProfile() {
        return repository.findByCurrentSettingsProfileIsTrue()
                .map(mapper::toDto)
                .orElseThrow(ActiveProfileNotInstalledException::new);
    }
}
