package ru.egartech.tmsystem.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.exception.CustomEntityNotFoundException;
import ru.egartech.tmsystem.exception.PositionConstraintException;
import ru.egartech.tmsystem.model.dto.PositionDto;
import ru.egartech.tmsystem.model.dto.PositionSummaryDto;
import ru.egartech.tmsystem.model.entity.Position;
import ru.egartech.tmsystem.model.mapping.PositionMapper;
import ru.egartech.tmsystem.model.repository.PositionRepository;
import ru.egartech.tmsystem.service.PositionService;
import ru.egartech.tmsystem.service.SettingsService;
import ru.egartech.tmsystem.utils.PeriodValidation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository repository;
    private final PositionMapper mapper;
    private final EntityManagerFactory entityManagerFactory;
    private final SettingsService settingsService;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PositionDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public PositionDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new CustomEntityNotFoundException(id));
    }


    @Override
    public PositionDto save(PositionDto dto) {
        PositionDto positionDto = new PositionDto();
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            positionDto = mapper.toDto(entityManager.merge(mapper.positionDtoToPosition(dto)));
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return positionDto;
    }

    @Transactional
    @Override
    public PositionDto updateById(Long id, PositionDto updated) {
        Position actual = mapper.positionDtoToPosition(findById(id));
        BeanUtils.copyProperties(mapper.positionDtoToPosition(updated), actual, "id");
        return mapper.toDto(entityManager.merge(actual));
    }

    @Override
    public void deleteById(Long id) {
        if (!findById(id).getEmployees().isEmpty()) {
            throw new PositionConstraintException();
        } else {
            repository.deleteById(id);
        }
    }

    @Override
    public List<PositionSummaryDto> positionSummaryByPeriod(LocalDate startDate, LocalDate endDate) {
        PeriodValidation.validatePeriod(30, 0, 0, startDate, endDate);
        return findAll().stream()
                .map(p -> mapper.toPositionSummaryDto(p, settingsService.findByCurrentSettingsProfile(),
                        startDate, endDate,
                        positionWorkTimeByPeriod(startDate, endDate, p.getId()),
                        positionDistractionTimeByPeriod(startDate, endDate, p.getId()),
                        positionRestTimeByPeriod(startDate, endDate, p.getId())))
                .toList();
    }

    @Override
    public long positionWorkTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id) {
        TypedQuery<Long> query = entityManager.createQuery(
                "select sum(t.workTime) " +
                        "from TimeSheet t join t.employee e " +
                        "where t.date >= :startDate " +
                        "and t.date <= :endDate " +
                        "and e.position.id = :id", Long.class);
        return Optional.ofNullable(query.setParameter("startDate", startDate)
                        .setParameter("endDate", endDate)
                        .setParameter("id", id).getSingleResult())
                .orElse(0L);
    }

    @Override
    public long positionDistractionTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id) {
        TypedQuery<Long> query = entityManager.createQuery(
                "select sum(d.distractionTime) " +
                        "from Distraction d join d.employee e " +
                        "where d.date >= :startDate " +
                        "and d.date <= :endDate " +
                        "and e.position.id = :id", Long.class);
        return Optional.ofNullable(query.setParameter("startDate", startDate)
                        .setParameter("endDate", endDate)
                        .setParameter("id", id).getSingleResult())
                .orElse(0L);
    }

    @Override
    public long positionRestTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id) {
        TypedQuery<Long> query = entityManager.createQuery(
                "select sum(r.restTime) " +
                        "from Rest r join r.employee e " +
                        "where r.date >= :startDate " +
                        "and r.date <= :endDate " +
                        "and e.position.id = :id", Long.class);
        return Optional.ofNullable(query.setParameter("startDate", startDate)
                        .setParameter("endDate", endDate)
                        .setParameter("id", id).getSingleResult())
                .orElse(0L);
    }

    @Override
    public PositionDto save(PositionDto positionDto, String departmentName) {
        return save(positionDto);
    }
}
