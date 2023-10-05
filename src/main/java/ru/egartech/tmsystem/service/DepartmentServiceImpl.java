package ru.egartech.tmsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.model.dto.DepartmentDto;
import ru.egartech.tmsystem.model.dto.DepartmentSummaryDto;
import ru.egartech.tmsystem.model.dto.FilterDto;
import ru.egartech.tmsystem.model.dto.SettingsDto;
import ru.egartech.tmsystem.model.entity.Department;
import ru.egartech.tmsystem.model.mapping.DepartmentMapper;
import ru.egartech.tmsystem.model.repository.*;
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
//    private final DistractionRepository distractionRepository;
//    private final RestRepository restRepository;
//    private final TimeSheetRepository timeSheetRepository;
    private final SummaryRepository summaryRepository;

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
                .map(d -> mapper.toDto(repository.save(d)))
                .orElseThrow();
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
            long workTime = departmentWorkTimeByDate(startDate, endDate, department.getId());
            long distractionTime = departmentDistractionTimeByDate(startDate, endDate, department.getId());
            long restTime = departmentRestTimeByDate(startDate, endDate, department.getId());
            long lunchTime = departmentLunchTimeByDate(startDate, endDate, department.getId());
            SummaryFormatter.toSummaryDto(workTime, distractionTime, restTime, lunchTime,
                    departmentSummaryDto, department, startDate, endDate, settings);

            departmentsSummary.add(departmentSummaryDto);
        }

        return departmentsSummary;
    }

    @Override
    public long departmentWorkTimeByDate(LocalDate startDate, LocalDate endDate, Long id) {
        return repository.departmentWorkTimeByPeriod(startDate, endDate, id);
    }

    @Override
    public long departmentDistractionTimeByDate(LocalDate startDate, LocalDate endDate, Long id) {
        return repository.departmentDistractionTimeByPeriod(startDate, endDate, id);
    }

    @Override
    public long departmentRestTimeByDate(LocalDate startDate, LocalDate endDate, Long id) {
        return repository.departmentRestTimeByPeriod(startDate, endDate, id);
    }

    @Override
    public long departmentLunchTimeByDate(LocalDate startDate, LocalDate endDate, Long id) {
        return repository.departmentLunchTimeByPeriod(startDate, endDate, id);
    }

    @Override
    public long summaryWorkTimeByDate(LocalDate startDate, LocalDate endDate) {
        return summaryRepository.summaryWorkTimeByPeriod(startDate, endDate);
    }

    @Override
    public long summaryDistractionTimeByDate(LocalDate startDate, LocalDate endDate) {
        return summaryRepository.summaryDistractionTimeByPeriod(startDate, endDate);
    }

    @Override
    public long summaryRestTimeByDate(LocalDate startDate, LocalDate endDate) {
        return summaryRepository.summaryRestTimeByPeriod(startDate, endDate);
    }

    @Override
    public long summaryLunchTimeByDate(LocalDate startDate, LocalDate endDate) {
        return summaryRepository.summaryLunchTimeByPeriod(startDate, endDate);
    }
}
