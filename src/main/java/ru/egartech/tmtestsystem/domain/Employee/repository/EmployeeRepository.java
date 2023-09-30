package ru.egartech.tmtestsystem.domain.Employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egartech.tmtestsystem.domain.Employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
