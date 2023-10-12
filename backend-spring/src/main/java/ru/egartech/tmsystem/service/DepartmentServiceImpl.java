package ru.egartech.tmsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.exception.DepartmentConstraintException;
import ru.egartech.tmsystem.exception.DepartmentNotFoundException;
import ru.egartech.tmsystem.exception.DurationException;
import ru.egartech.tmsystem.exception.StartDateEarlierException;
import ru.egartech.tmsystem.model.dto.DepartmentDto;
import ru.egartech.tmsystem.model.dto.DepartmentSummaryDto;
import ru.egartech.tmsystem.model.dto.SettingsDto;
import ru.egartech.tmsystem.model.entity.Department;
import ru.egartech.tmsystem.model.mapping.DepartmentMapper;
import ru.egartech.tmsystem.model.repository.DepartmentRepository;
import ru.egartech.tmsystem.utils.SummaryFormatter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

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
    public DepartmentDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
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
        findAll().forEach((d) -> {
            if (d.getPositions().isEmpty()) {
                throw new DepartmentConstraintException();
            }
        });
        repository.deleteById(id);
    }

    @Override
    public List<DepartmentSummaryDto> departmentsSummary(LocalDateTime startDate, LocalDateTime endDate) {

        if (Duration.between(startDate, endDate).toDays() > 30) {
            throw new DurationException(30);
        }

        if (startDate.isAfter(endDate)) {
            throw new StartDateEarlierException();
        }

        List<DepartmentSummaryDto> departmentsSummary = new ArrayList<>();
        List<DepartmentDto> departments = findAll();
        SettingsDto settings = settingsService.findByCurrentSettingsProfile();

        for (DepartmentDto department : departments) {

            DepartmentSummaryDto departmentSummaryDto = new DepartmentSummaryDto();
            departmentSummaryDto.setId(department.getId());
            long workTime = departmentWorkTimeByPeriod(startDate.toLocalDate(), endDate.toLocalDate(), department.getId());
            long distractionTime = departmentDistractionTimeByPeriod(startDate.toLocalDate(), endDate.toLocalDate(), department.getId());
            long restTime = departmentRestTimeByPeriod(startDate.toLocalDate(), endDate.toLocalDate(), department.getId());
            SummaryFormatter.toSummaryDto(workTime, distractionTime, restTime,
                    departmentSummaryDto, department, startDate.toLocalDate(), endDate.toLocalDate(), settings);

            departmentsSummary.add(departmentSummaryDto);
        }

        return departmentsSummary;
    }

    //Если не выбран никакой период, задает по умолчанию текущий месяц
    @Override
    public List<DepartmentSummaryDto> departmentsSummary() {
        return departmentsSummary(LocalDateTime.of(YearMonth.now().getYear(), YearMonth.now().getMonth(), 1, 0, 0, 0),
                LocalDateTime.of(YearMonth.now().getYear(), YearMonth.now().getMonth(), YearMonth.now().atEndOfMonth().getDayOfMonth(), 0, 0, 0));
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
