package ru.egartech.tmsystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.egartech.tmsystem.model.repository.DepartmentRepository;
import ru.egartech.tmsystem.model.repository.EmployeeRepository;
import ru.egartech.tmsystem.model.repository.TimeSheetRepository;

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
