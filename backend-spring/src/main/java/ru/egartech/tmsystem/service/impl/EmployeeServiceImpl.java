package ru.egartech.tmsystem.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.exception.CustomEntityNotFoundException;
import ru.egartech.tmsystem.model.dto.EditEmployeeDto;
import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.dto.EmployeeSummaryDto;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.model.entity.TimeSheet;
import ru.egartech.tmsystem.model.mapping.EmployeeMapper;
import ru.egartech.tmsystem.model.repository.EmployeeRepository;
import ru.egartech.tmsystem.service.*;
import ru.egartech.tmsystem.utils.PeriodValidation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;
    private final SettingsService settingsService;
    private final TimeSheetService timeSheetService;
    private final RestService restService;
    private final DistractionService distractionService;


    @PersistenceContext
    private EntityManager entityManager;


    public EmployeeServiceImpl(@Qualifier("employeeRepository") EmployeeRepository repository, EmployeeMapper mapper,
                               SettingsService settingsService, TimeSheetService timeSheetService, RestService restService,
                               DistractionService distractionService) {
        this.repository = repository;
        this.mapper = mapper;
        this.settingsService = settingsService;
        this.distractionService = distractionService;
        this.timeSheetService = timeSheetService;
        this.restService = restService;
    }

    @Override
    public List<EmployeeDto> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);
        cq.multiselect(root.get("name"), root.get("age"), root.get("position"), root.get("privilegesNumber"), root.get("id"));
        List<Employee> result = entityManager.createQuery(cq).getResultList();
        return result.stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public EmployeeDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new CustomEntityNotFoundException(id));
    }

    @Override
    public EmployeeDto save(EmployeeDto dto) {
        Employee employee = repository.save(mapper.toEntity(dto));
        return mapper.toDto(employee);
    }

    @Override
    public EmployeeDto updateById(Long id, EmployeeDto dto) {
        return repository.findById(id)
                .map(entity -> {
                    BeanUtils.copyProperties(mapper.toEntity(dto), entity, "id");
                    return mapper.toDto(repository.save(entity));
                })
                .orElseThrow(() -> new CustomEntityNotFoundException(id));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<EmployeeSummaryDto> employeeSummaryByPeriod(LocalDate startDate, LocalDate endDate) {
        PeriodValidation.validatePeriod(30, 0, 0, startDate, endDate);
        return findAll().stream()
                .map(e -> mapper.toEmployeeSummaryDto(e,
                        settingsService.findByCurrentSettingsProfile(),
                        startDate, endDate,
                        employeeWorkTimeByPeriod(startDate, endDate, e.getId()),
                        employeeDistractionTimeByPeriod(startDate, endDate, e.getId()),
                        employeeRestTimeByPeriod(startDate, endDate, e.getId())))
                .toList();
    }

    @Override
    public long employeeWorkTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TimeSheet> root = cq.from(TimeSheet.class);
        Predicate greaterThanDate = cb.greaterThanOrEqualTo(root.get("date"), startDate);
        Predicate lessThanDate = cb.lessThanOrEqualTo(root.get("date"), endDate);
        Predicate equalId = cb.equal(root.get("employee").get("id"), id);
        cq.select(cb.sum(root.get("workTime")))
                .where(cb.and(greaterThanDate, lessThanDate, equalId));
        return Optional.ofNullable(entityManager.createQuery(cq).getSingleResult())
                .orElse(0L);
    }

    @Override
    public long employeeDistractionTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id) {
        return repository.employeeDistractionTimeByPeriod(startDate, endDate, id)
                .orElse(0L);
    }

    @Override
    public long employeeRestTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id) {
        return repository.employeeRestTimeByPeriod(startDate, endDate, id)
                .orElse(0L);
    }


    @Override
    public EmployeeDto update(EmployeeDto employeeDto) {
        return updateById(employeeDto.getId(), employeeDto);
    }

    @Override
    public EditEmployeeDto update(EditEmployeeDto editEmployeeDto) {
        updateById(editEmployeeDto.getId(), mapper.toDto(editEmployeeDto));
        return getEditEmployeeDtoById(editEmployeeDto.getId());
    }

    @Override
    public EditEmployeeDto save(EditEmployeeDto dto) {
        EmployeeDto employee = save(mapper.toDto(dto));
        return getEditEmployeeDtoById(employee.getId());
    }

    @Override
    public List<EmployeeDto> findAllByPeriod(LocalDate startDate, LocalDate endDate) {
        PeriodValidation.validatePeriod(30, 0, 0, startDate, endDate);
        return findAll().stream()
                .map(e -> mapper.toEmployeeDtoByPeriod(e,
                        timeSheetService.findByDateBetweenAndEmployee_Id(startDate, endDate, e.getId()),
                        restService.findByDateBetweenAndEmployee_Id(startDate, endDate, e.getId()),
                        distractionService.findByDateBetweenAndEmployee_Id(startDate, endDate, e.getId())
                ))
                .toList();
    }

    @Override
    public EditEmployeeDto getEditEmployeeDtoById(Long id) {
        return mapper.toEditEmployeeDto(findById(id));
    }
}
