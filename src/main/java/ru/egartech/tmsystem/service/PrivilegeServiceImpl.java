package ru.egartech.tmsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
                .map(p -> mapper.toDto(p))
                .toList();
    }

    @Override
    public Optional<PrivilegeDto> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public PrivilegeDto updateById(Long id, PrivilegeDto dto) {
        return null;
    }
}
