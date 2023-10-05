package ru.egartech.tmsystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.egartech.tmsystem.model.entity.Rest;

import java.time.LocalDate;

public interface RestRepository extends JpaRepository<Rest, Long> {

    @Query("select sum(r.restTime) from TimeSheet t join t.rests r join t.employees e " +
            "where t.date >= :startDate and t.date <= :endDate")
    long summaryRestTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("select sum(r.lunchTime) from TimeSheet t join t.rests r join t.employees e " +
            "where t.date >= :startDate and t.date <= :endDate")
    long summaryLunchTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
