package ru.egartech.tmsystem.domain.employee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.egartech.tmsystem.domain.department.entity.Department;
import ru.egartech.tmsystem.domain.employee.entity.Employee;
import ru.egartech.tmsystem.domain.department.repository.DepartmentRepository;
import ru.egartech.tmsystem.domain.employee.repository.EmployeeRepository;
import ru.egartech.tmsystem.domain.timesheet.repository.TimeSheetRepository;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class EmployeeController {

    private TimeSheetRepository timeManagementRepository;
    private EmployeeRepository repository;
    private DepartmentRepository departmentRepository;

    public EmployeeController(EmployeeRepository repository, DepartmentRepository departmentRepository,
                              TimeSheetRepository timeManagementRepository) {
        this.repository = repository;
        this.departmentRepository = departmentRepository;
        this.timeManagementRepository = timeManagementRepository;
    }


}
