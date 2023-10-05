package ru.egartech.tmsystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.egartech.tmsystem.model.entity.TimeSheet;

import java.time.LocalDate;
import java.time.LocalTime;

public interface TimeSheetRepository extends JpaRepository<TimeSheet, Long> {
    @Query("select sum(t.workTime) from TimeSheet t join t.employees e where t.date >= :startDate " +
            "and t.date <= :endDate")
    long summaryWorkTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
