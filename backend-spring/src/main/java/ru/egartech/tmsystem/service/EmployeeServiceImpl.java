package ru.egartech.tmsystem.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.exception.EmployeeNotFoundException;
import ru.egartech.tmsystem.model.dto.EditEmployeeDto;
import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.dto.EmployeeSummaryDto;
import ru.egartech.tmsystem.model.dto.SettingsDto;
import ru.egartech.tmsystem.model.entity.Department;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.model.entity.Position;
import ru.egartech.tmsystem.model.entity.TimeSheet;
import ru.egartech.tmsystem.model.mapping.EmployeeMapper;
import ru.egartech.tmsystem.model.repository.DistractionRepository;
import ru.egartech.tmsystem.model.repository.EmployeeRepository;
import ru.egartech.tmsystem.model.repository.RestRepository;
import ru.egartech.tmsystem.model.repository.TimeSheetRepository;
import ru.egartech.tmsystem.utils.BitsConverter;
import ru.egartech.tmsystem.utils.PeriodValidation;
import ru.egartech.tmsystem.utils.SummaryFormatter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;
    private final SettingsService settingsService;
    private final PositionService positionService;
    private  final TimeSheetRepository timeSheetRepository;

    private  final RestRepository restRepository;

    private  final DistractionRepository distractionRepository;

    @PersistenceContext
    private EntityManager entityManager;



    public EmployeeServiceImpl(@Qualifier("employeeRepository") EmployeeRepository repository, EmployeeMapper mapper,
                               SettingsService settingsService, PositionService positionService,
                               TimeSheetRepository timeSheetRepository, RestRepository restRepository, DistractionRepository distractionRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.settingsService = settingsService;
        this.positionService = positionService;
        this.distractionRepository = distractionRepository;
        this.restRepository = restRepository;
        this.timeSheetRepository = timeSheetRepository;

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
    public List<EmployeeSummaryDto> employeeSummaryByPeriod(LocalDate startDate, LocalDate endDate) {

        PeriodValidation.validatePeriod(30, 0, 0, startDate, endDate);

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

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TimeSheet> root = cq.from(TimeSheet.class);
        Predicate greaterThanDate = cb.greaterThanOrEqualTo(root.get("date"), startDate);
        Predicate lessThanDate = cb.lessThanOrEqualTo(root.get("date"), endDate);
        Predicate equalId = cb.equal(root.get("employee").get("id"), id);
        cq.select(cb.sum(root.get("workTime")))
                .where(cb.and(greaterThanDate, lessThanDate, equalId));

        return entityManager.createQuery(cq).getSingleResult();

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

        PeriodValidation.validatePeriod(10, 0, 0, startDate, endDate);

        List<EmployeeDto> result = new ArrayList<>();
        for (EmployeeDto employeeDto : findAll()) {
            EmployeeDto dto = new EmployeeDto();
            dto.setId(employeeDto.getId());
            dto.setName(employeeDto.getName());
            dto.setAge(employeeDto.getAge());
            dto.setPosition(employeeDto.getPosition());
            dto.setPrivilegesNumber(employeeDto.getPrivilegesNumber());
            dto.setPosition(employeeDto.getPosition());
            dto.setRests(restRepository.findByDateBetweenAndEmployee_Id(startDate, endDate, employeeDto.getId()));
            dto.setDistractions(distractionRepository.findByDateBetweenAndEmployee_Id(startDate, endDate, employeeDto.getId()));
            dto.setTimeSheets(timeSheetRepository.findByDateBetweenAndEmployee_Id(startDate, endDate, employeeDto.getId()));

            result.add(dto);
        }

        return result;
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
