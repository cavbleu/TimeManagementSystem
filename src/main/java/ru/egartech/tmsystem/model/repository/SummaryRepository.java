package ru.egartech.tmsystem.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SummaryRepository extends TimeSheetRepository{

    @Query("select sum(t.workTime) " +
            "from TimeSheet t join t.employees e " +
            "where t.date >= :startDate " +
            "and t.date <= :endDate")
    Optional<Long> summaryWorkTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("select sum(d.distractionTime) " +
            "from TimeSheet t join t.distractions d join t.employees e " +
            "where t.date >= :startDate " +
            "and t.date <= :endDate")
    Optional<Long> summaryDistractionTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("select sum(r.restTime) from TimeSheet t join t.rests r join t.employees e " +
            "where t.date >= :startDate " +
            "and t.date <= :endDate")
    Optional<Long> summaryRestTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
