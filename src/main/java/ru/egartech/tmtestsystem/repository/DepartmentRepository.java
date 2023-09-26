package ru.egartech.tmtestsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egartech.tmtestsystem.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
