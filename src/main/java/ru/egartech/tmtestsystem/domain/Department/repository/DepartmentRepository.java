package ru.egartech.tmtestsystem.domain.Department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egartech.tmtestsystem.domain.Department.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
