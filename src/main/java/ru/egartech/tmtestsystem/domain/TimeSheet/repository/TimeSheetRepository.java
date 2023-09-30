package ru.egartech.tmtestsystem.domain.TimeSheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSheetRepository extends JpaRepository<ru.egartech.tmtestsystem.domain.TimeSheet.entity.TimeSheet, Long> {
}
