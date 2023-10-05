//package ru.egartech.tmsystem.model.repository;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDate;
//
//@Repository
//public interface SummaryRepository {
//
//    @Query("select sum(t.workTime) from TimeSheet t join t.employees e where t.date >= :startDate " +
//            "and t.date <= :endDate")
//    long summaryWorkTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
//
//    @Query("select sum(d.distractionTime) from TimeSheet t join t.distractions d join t.employees e " +
//            "where t.date >= :startDate and t.date <= :endDate")
//    long summaryDistractionTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
//
//    @Query("select sum(r.restTime) from TimeSheet t join t.rests r join t.employees e " +
//            "where t.date >= :startDate and t.date <= :endDate")
//    long summaryRestTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
//
//    @Query("select sum(r.lunchTime) from TimeSheet t join t.rests r join t.employees e " +
//            "where t.date >= :startDate and t.date <= :endDate")
//    long summaryLunchTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
//}
