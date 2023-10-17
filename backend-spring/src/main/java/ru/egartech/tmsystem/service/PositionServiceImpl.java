package ru.egartech.tmsystem.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.exception.DepartmentConstraintException;
import ru.egartech.tmsystem.model.dto.EditPositionDto;
import ru.egartech.tmsystem.model.dto.PositionDto;
import ru.egartech.tmsystem.model.dto.PositionSummaryDto;
import ru.egartech.tmsystem.model.dto.SettingsDto;
import ru.egartech.tmsystem.model.entity.Position;
import ru.egartech.tmsystem.model.mapping.PositionMapper;
import ru.egartech.tmsystem.model.repository.PositionRepository;
import ru.egartech.tmsystem.utils.PeriodValidation;
import ru.egartech.tmsystem.utils.SummaryFormatter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository repository;
    private final PositionMapper mapper;
    private final SettingsService settingsService;
    private final DepartmentService departmentService;
    private final EntityManagerFactory entityManagerFactory;

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
        Position position = entityManager.find(Position.class, id);
        return Optional.of(position)
                .map(mapper::toDto)
                .orElseThrow();
    }


    @Override
    public PositionDto save(PositionDto dto) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        PositionDto positionDto = mapper.toDto(entityManager.merge(mapper.toEntity(dto)));
        entityManager.getTransaction().commit();
        return positionDto;
    }

    @Transactional
    @Override
    public PositionDto updateById(Long id, PositionDto updated) {
        Position actual = mapper.toEntity(findById(id));
        BeanUtils.copyProperties(mapper.toEntity(updated), actual, "id");
        return mapper.toDto(entityManager.merge(actual));
    }

    @Override
    public void deleteById(Long id) {
        if (!findById(id).getEmployees().isEmpty()) {
            throw new DepartmentConstraintException();
        } else {
            repository.deleteById(id);
        }
    }

    @Override
    public List<PositionSummaryDto> positionsSummaryByPeriod(LocalDate startDate, LocalDate endDate) {

        PeriodValidation.validatePeriod(30,0,0, startDate, endDate);

        List<PositionSummaryDto> positionsSummary = new ArrayList<>();
        List<PositionDto> positions = findAll();
        SettingsDto settings = settingsService.findByCurrentSettingsProfile();

        for (PositionDto position : positions) {

            PositionSummaryDto positionSummaryDto = new PositionSummaryDto();
            positionSummaryDto.setId(position.getId());
            long workTime = positionWorkTimeByPeriod(startDate, endDate, position.getId());
            long distractionTime = positionDistractionTimeByPeriod(startDate, endDate, position.getId());
            long restTime = positionRestTimeByPeriod(startDate, endDate, position.getId());
            SummaryFormatter.toSummaryDto(workTime, distractionTime, restTime,
                    positionSummaryDto, position, startDate, endDate, settings);
            positionSummaryDto.setDepartmentName(position.getDepartment().getName());
            positionSummaryDto.setPositionName(position.getName());
            positionsSummary.add(positionSummaryDto);
        }

        return positionsSummary;
    }

    @Override
    public long positionWorkTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id) {
        TypedQuery<Long> query = entityManager.createQuery(
                "select sum(t.workTime) " +
                        "from TimeSheet t join t.employee e " +
                        "where t.date >= :startDate " +
                        "and t.date <= :endDate " +
                        "and e.position.id = :id", Long.class);
        return Optional.of(query.setParameter("startDate", startDate)
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
        return Optional.of(query.setParameter("startDate", startDate)
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
        return Optional.of(query.setParameter("startDate", startDate)
                        .setParameter("endDate", endDate)
                        .setParameter("id", id).getSingleResult())
                .orElse(0L);
    }

    @Override
    public PositionDto save(PositionDto positionDto, String departmentName) {
        return save(positionDto);
    }

    @Transactional
    @Override
    public PositionDto update(PositionDto positionDto) {
        return updateById(positionDto.getId(), positionDto);
    }

    @Override
    public EditPositionDto getEditPositionDtoById(Long id) {
        EditPositionDto editPositionDto = new EditPositionDto();
        PositionDto positionDto = findById(id);
        editPositionDto.setId(positionDto.getId());
        editPositionDto.setName(positionDto.getName());
        editPositionDto.setDepartment(positionDto.getDepartment());
        editPositionDto.setAllDepartments(departmentService.findAll());
        return editPositionDto;
    }
}
