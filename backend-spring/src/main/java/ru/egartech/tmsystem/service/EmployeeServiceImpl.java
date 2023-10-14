package ru.egartech.tmsystem.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.exception.DurationException;
import ru.egartech.tmsystem.exception.EmployeeNotFoundException;
import ru.egartech.tmsystem.exception.StartDateEarlierException;
import ru.egartech.tmsystem.model.dto.*;
import ru.egartech.tmsystem.model.entity.Distraction;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.model.entity.Rest;
import ru.egartech.tmsystem.model.entity.TimeSheet;
import ru.egartech.tmsystem.model.mapping.EmployeeMapper;
import ru.egartech.tmsystem.model.repository.EmployeeRepository;
import ru.egartech.tmsystem.utils.BitsConverter;
import ru.egartech.tmsystem.utils.SummaryFormatter;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;
    private final SettingsService settingsService;
    private final PositionService positionService;

    @PersistenceContext
    private EntityManager entityManager;


    public EmployeeServiceImpl(@Qualifier("employeeRepository") EmployeeRepository repository, EmployeeMapper mapper,
                               SettingsService settingsService, PositionService positionService) {
        this.repository = repository;
        this.mapper = mapper;
        this.settingsService = settingsService;
        this.positionService = positionService;
    }

    @Override
    public List<EmployeeDto> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);
        Fetch<Employee, TimeSheet> b = root.fetch("timeSheets", JoinType.LEFT);
        Fetch<TimeSheet, Rest> r = b.fetch("rests", JoinType.LEFT);
        Fetch<TimeSheet, Distraction> d = b.fetch("distractions", JoinType.LEFT);
        cq.select(root);
        List<Employee> result = entityManager.createQuery(cq).getResultList();
        return result.stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public EmployeeDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
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
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<EmployeeSummaryDto> employeesSummaryByPeriod(LocalDate startDate, LocalDate endDate) {

        if (Period.between(startDate, endDate).getDays() > 30) {
            throw new DurationException(30);
        }

        if (startDate.isAfter(endDate)) {
            throw new StartDateEarlierException();
        }

        List<EmployeeSummaryDto> employeesSummary = new ArrayList<>();
        List<EmployeeDto> employees = findAll();
        SettingsDto settings = settingsService.findByCurrentSettingsProfile();

        for (EmployeeDto employee : employees) {

            EmployeeSummaryDto employeeSummaryDto = new EmployeeSummaryDto();
            employeeSummaryDto.setId(employee.getId());
            employeeSummaryDto.setAge(employee.getAge());
            long workTime = employeeWorkTimeByPeriod(startDate, endDate, employee.getId());
            long distractionTime = employeeDistractionTimeByPeriod(startDate, endDate, employee.getId());
            long restTime = employeeRestTimeByPeriod(startDate, endDate, employee.getId());
            SummaryFormatter.toSummaryDto(workTime, distractionTime, restTime,
                    employeeSummaryDto, employee, startDate, endDate, settings);
            employeeSummaryDto.setEmployeeName(employee.getName());
            employeeSummaryDto.setPositionName(employee.getPosition().getName());
            employeeSummaryDto.setDepartmentName(employee.getPosition().getDepartment().getName());
            employeeSummaryDto.setPrivileges(String.join("; ", employee.getPrivileges()));
            employeesSummary.add(employeeSummaryDto);
        }

        return employeesSummary;
    }

    @Override
    public long employeeWorkTimeByPeriod(LocalDate startDate, LocalDate endDate, Long id) {
        return repository.employeeWorkTimeByPeriod(startDate, endDate, id)
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
        if (Period.between(startDate, endDate).getDays() > 10) {
            throw new DurationException(10);
        }
        if (startDate.isAfter(endDate)) {
            throw new StartDateEarlierException();
        }

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);
        Fetch<Employee, TimeSheet> b = root.fetch("timeSheets", JoinType.LEFT);
        Fetch<TimeSheet, Rest> r = b.fetch("rests", JoinType.LEFT);
        Fetch<TimeSheet, Distraction> d = b.fetch("distractions", JoinType.LEFT);

        Predicate greaterThanDate = cb.greaterThanOrEqualTo(root.join("timeSheets").get("date"), startDate);
        Predicate lessThanDate = cb.lessThanOrEqualTo(root.join("timeSheets").get("date"), endDate);

        cq.select(root).where(cb.and(greaterThanDate, lessThanDate));

        List<Employee> result = entityManager.createQuery(cq).getResultList();

        return entityManager.createQuery(cq).getResultList().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public EditEmployeeDto getEditEmployeeDtoById(Long employeeId) {
        EditEmployeeDto editEmployeeDto = new EditEmployeeDto();
        EmployeeDto employeeDto = findById(employeeId);
        editEmployeeDto.setId(employeeId);
        editEmployeeDto.setName(employeeDto.getName());
        editEmployeeDto.setAge(employeeDto.getAge());
        editEmployeeDto.setPosition(employeeDto.getPosition());
        editEmployeeDto.setPrivilegesNumber(employeeDto.getPrivilegesNumber());
        editEmployeeDto.setAllPositions(positionService.findAll());
        BitsConverter.setEmployeePrivileges(editEmployeeDto);
        return editEmployeeDto;
    }
}
