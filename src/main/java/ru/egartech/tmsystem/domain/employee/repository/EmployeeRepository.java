package ru.egartech.tmsystem.domain.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egartech.tmsystem.domain.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
