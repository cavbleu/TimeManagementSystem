package ru.egartech.tmtestsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egartech.tmtestsystem.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
