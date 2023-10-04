package ru.egartech.tmsystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.egartech.tmsystem.model.entity.Department;

import java.time.LocalDate;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("select sum(t.workTime) from TimeSheet t join t.employees e where t.date >= :startDate " +
            "and t.date <= :endDate and e.department.id = :id")
    long departmentWorkTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                    @Param("id") Long id);

    @Query("select sum(d.distractionTime) from TimeSheet t join t.distractions d join t.employees e " +
            "where t.date >= :startDate and t.date <= :endDate and e.department.id = :id")
    long departmentDistractionTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                           @Param("id") Long id);

    @Query("select sum(r.restTime) from TimeSheet t join t.rests r join t.employees e " +
            "where t.date >= :startDate and t.date <= :endDate and e.department.id = :id")
    long departmentRestTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                    @Param("id") Long id);

    @Query("select sum(r.lunchTime) from TimeSheet t join t.rests r join t.employees e " +
            "where t.date >= :startDate and t.date <= :endDate and e.department.id = :id")
    long departmentLunchTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                     @Param("id") Long id);

}
