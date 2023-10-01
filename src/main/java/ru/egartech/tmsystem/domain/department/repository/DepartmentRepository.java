package ru.egartech.tmsystem.domain.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egartech.tmsystem.domain.department.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
