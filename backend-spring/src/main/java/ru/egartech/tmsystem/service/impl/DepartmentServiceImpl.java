package ru.egartech.tmsystem.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.exception.CustomEntityNotFoundException;
import ru.egartech.tmsystem.exception.DepartmentConstraintException;
import ru.egartech.tmsystem.model.dto.DepartmentDto;
import ru.egartech.tmsystem.model.dto.DepartmentSummaryDto;
import ru.egartech.tmsystem.model.entity.Department;
import ru.egartech.tmsystem.model.entity.Position;
import ru.egartech.tmsystem.model.mapping.DepartmentMapper;
import ru.egartech.tmsystem.model.repository.DepartmentRepository;
import ru.egartech.tmsystem.service.DepartmentService;
import ru.egartech.tmsystem.service.SettingsService;
import ru.egartech.tmsystem.utils.PeriodValidation;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;
    private final SettingsService settingsService;
    private final DepartmentMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DepartmentDto> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Department> cq = cb.createQuery(Department.class);
        Root<Department> root = cq.from(Department.class);
        Fetch<Department, Position> d = root.fetch("positions", JoinType.LEFT);
        cq.select(root);
        List<Department> result =  entityManager.createQuery(cq).getResultList();
        return result.stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public DepartmentDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new CustomEntityNotFoundException(id));
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
                .orElseThrow(() -> new CustomEntityNotFoundException(id));
    }

    @Override
    public void deleteById(Long id) {
        if (!findById(id).getPositions().isEmpty()) {
            throw new DepartmentConstraintException();
        } else {
            repository.deleteById(id);
        }
    }

    @Override
    public List<DepartmentSummaryDto> departmentSummaryByPeriod(LocalDate startDate, LocalDate endDate) {
        PeriodValidation.validatePeriod(30,0,0, startDate, endDate);
        return findAll().stream()
                .map(d -> mapper.toDepartmentSummaryDto(d,settingsService.findByCurrentSettingsProfile(),
                        startDate, endDate,
                        departmentWorkTimeByPeriod(startDate, endDate, d.getId()),
                        departmentDistractionTimeByPeriod(startDate, endDate, d.getId()),
                        departmentRestTimeByPeriod(startDate, endDate, d.getId())))
                .toList();
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

}
