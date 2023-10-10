package ru.egartech.tmsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.exception.PrivilegeNotFoundException;
import ru.egartech.tmsystem.model.dto.PrivilegeDto;
import ru.egartech.tmsystem.model.mapping.PrivilegeMapper;
import ru.egartech.tmsystem.model.repository.PrivilegeRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrivilegeServiceImpl implements PrivilegeService{

    private final PrivilegeRepository repository;
    private final PrivilegeMapper mapper;
    @Override
    public List<PrivilegeDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public Optional<PrivilegeDto> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public PrivilegeDto updateById(Long id, PrivilegeDto dto) {
        return repository.findById(id)
                .map(entity -> {
                    BeanUtils.copyProperties(mapper.toEntity(dto), entity, "id");
                    return mapper.toDto(repository.save(entity));
                })
                .orElseThrow(() -> new PrivilegeNotFoundException(id));
    }

    @Override
    public PrivilegeDto increasedAmountByPrivilege(String privilegeName) {
        return repository.increasedAmountByPrivilege(privilegeName)
                .map(mapper::toDto)
                .orElseThrow(() -> new PrivilegeNotFoundException(privilegeName));
    }
}
