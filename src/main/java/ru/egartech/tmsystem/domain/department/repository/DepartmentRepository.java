package ru.egartech.tmsystem.domain.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.egartech.tmsystem.domain.department.entity.Department;

import java.time.LocalDate;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("select sum(t.workTime) from TimeSheet t join t.employees e where t.date >= :startDate " +
            "and t.date <= :endDate and e.department.name = :name")
    long departmentWorkTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                    @Param("name") String departmentName);

    @Query("select sum(d.distractionTime) from TimeSheet t join t.distractions d join t.employees e " +
            "where t.date >= :startDate and t.date <= :endDate and e.department.name = :name")
    long departmentDistractionTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                           @Param("name") String departmentName);

    @Query("select sum(r.restTime) from TimeSheet t join t.rests r join t.employees e " +
            "where t.date >= :startDate and t.date <= :endDate and e.department.name = :name")
    long departmentRestTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                    @Param("name") String departmentName);

    @Query("select sum(r.lunchTime) from TimeSheet t join t.rests r join t.employees e " +
            "where t.date >= :startDate and t.date <= :endDate and e.department.name = :name")
    long departmentLunchTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                     @Param("name") String departmentName);

    @Query("select sum(t.workTime) from TimeSheet t join t.employees e where t.date >= :startDate " +
            "and t.date <= :endDate")
    long summaryWorkTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("select sum(d.distractionTime) from TimeSheet t join t.distractions d join t.employees e " +
            "where t.date >= :startDate and t.date <= :endDate")
    long summaryDistractionTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("select sum(r.restTime) from TimeSheet t join t.rests r join t.employees e " +
            "where t.date >= :startDate and t.date <= :endDate")
    long summaryRestTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("select sum(r.lunchTime) from TimeSheet t join t.rests r join t.employees e " +
            "where t.date >= :startDate and t.date <= :endDate")
    long summaryLunchTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
