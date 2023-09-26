package ru.egartech.tmtestsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egartech.tmtestsystem.entity.TimeManagement;

public interface TimeManagementRepository extends JpaRepository<TimeManagement, Long> {
}
