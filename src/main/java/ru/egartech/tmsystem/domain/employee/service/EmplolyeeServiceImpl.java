package ru.egartech.tmsystem.domain.employee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.domain.employee.dto.EmployeeDto;
import ru.egartech.tmsystem.domain.employee.entity.Employee;
import ru.egartech.tmsystem.domain.employee.mapper.EmployeeMapper;
import ru.egartech.tmsystem.domain.employee.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class EmplolyeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    @Override
    public List<EmployeeDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public Optional<EmployeeDto> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public EmployeeDto save(EmployeeDto dto) {
        Employee employee = repository.save(mapper.toEntity(dto));
        return mapper.toDto(employee);
    }

    @Override
    public EmployeeDto updateById(Long id, EmployeeDto dto) {
        return repository.findById(id)
                .map(e -> mapper.toDto(repository.save(e)))
                .orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
