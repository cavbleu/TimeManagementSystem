package ru.egartech.tmsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.exception.DepartmentNotFoundException;
import ru.egartech.tmsystem.model.dto.DepartmentDto;
import ru.egartech.tmsystem.model.dto.DepartmentSummaryDto;
import ru.egartech.tmsystem.model.dto.SettingsDto;
import ru.egartech.tmsystem.model.entity.Department;
import ru.egartech.tmsystem.model.mapping.DepartmentMapper;
import ru.egartech.tmsystem.model.repository.DepartmentRepository;
import ru.egartech.tmsystem.utils.SummaryFormatter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;
    private final SettingsService settingsService;
    private final DepartmentMapper mapper;

    @Override
    public List<DepartmentDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public Optional<DepartmentDto> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public DepartmentDto save(DepartmentDto dto) {
        Department department = repository.save(mapper.toEntity(dto));
        return mapper.toDto(department);
    }

    @Override
    public DepartmentDto updateById(Long id, DepartmentDto dto) {
        return repository.findById(id)
                .map(entity -> {
            BeanUtils.copyProperties(mapper.toEntity(dto), entity, "id");
            return mapper.toDto(repository.save(entity));
        })
                .orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<DepartmentSummaryDto> departmentsSummary(LocalDate startDate, LocalDate endDate) {

        List<DepartmentSummaryDto> departmentsSummary = new ArrayList<>();
        List<DepartmentDto> departments = findAll();
        SettingsDto settings = settingsService.findByCurrentSettingsProfile();

        for (DepartmentDto department : departments) {

            DepartmentSummaryDto departmentSummaryDto = new DepartmentSummaryDto();
            long workTime = departmentWorkTimeByPeriod(startDate, endDate, department.getId());
            long distractionTime = departmentDistractionTimeByPeriod(startDate, endDate, department.getId());
            long restTime = departmentRestTimeByPeriod(startDate, endDate, department.getId());
            SummaryFormatter.toSummaryDto(workTime, distractionTime, restTime,
                    departmentSummaryDto, department, startDate, endDate, settings);

            departmentsSummary.add(departmentSummaryDto);
        }

        return departmentsSummary;
    }

    @Override
    public long departmentWorkTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id) {

        return repository.departmentWorkTimeByPeriod(startDate, endDate, id)
                .orElse(0L);
    }

    @Override
    public long departmentDistractionTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id) {
        return repository.departmentDistractionTimeByPeriod(startDate, endDate, id)
                .orElse(0L);
    }

    @Override
    public long departmentRestTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id) {
        return repository.departmentRestTimeByPeriod(startDate, endDate, id)
                .orElse(0L);
    }

    @Override
    public Department findByName(String name) {
        return repository.findDepartmentByName(name)
                .orElseThrow(() -> new DepartmentNotFoundException(name));
    }
}
