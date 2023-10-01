package ru.egartech.tmsystem.domain.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSheetRepository extends JpaRepository<ru.egartech.tmsystem.domain.timesheet.entity.TimeSheet, Long> {
}
