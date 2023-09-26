package ru.egartech.tmtestsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.egartech.tmtestsystem.entity.Department;
import ru.egartech.tmtestsystem.entity.Employee;
import ru.egartech.tmtestsystem.entity.TimeManagement;
import ru.egartech.tmtestsystem.repository.DepartmentRepository;
import ru.egartech.tmtestsystem.repository.EmployeeRepository;
import ru.egartech.tmtestsystem.repository.TimeManagementRepository;

import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class EmployeeController {

    private TimeManagementRepository timeManagementRepository;
    private EmployeeRepository repository;
    private DepartmentRepository departmentRepository;

    public EmployeeController(EmployeeRepository repository, DepartmentRepository departmentRepository,
                              TimeManagementRepository timeManagementRepository) {
        this.repository = repository;
        this.departmentRepository = departmentRepository;
        this.timeManagementRepository = timeManagementRepository;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @PostMapping
    public Employee save() {
        Department department = departmentRepository.findById(2L).get();
        Employee emp = new Employee("John", "Ceena", 21, "Security", department);
        repository.save(emp);
        return emp;
    }

    @GetMapping("/time")
    public List<TimeManagement> getAllTimes() {
        return timeManagementRepository.findAll();
    }
}
