package ru.egartech.tmsystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egartech.tmsystem.model.entity.TimeSheet;

import java.time.LocalDate;
import java.util.List;

public interface TimeSheetRepository extends JpaRepository<TimeSheet, Long> {

    List<TimeSheet> findByDateBetweenAndEmployee_Id(LocalDate startDate, LocalDate endDate, Long empId);

}
