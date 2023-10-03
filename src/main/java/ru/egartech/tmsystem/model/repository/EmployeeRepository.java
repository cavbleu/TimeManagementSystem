package ru.egartech.tmsystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.egartech.tmsystem.model.entity.Employee;

import java.time.LocalDate;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select sum(t.workTime) from TimeSheet t join t.employees e where t.date >= :startDate " +
            "and t.date <= :endDate and e.fullName = :name")
    long employeeWorkTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                  @Param("name") String employeeName);

    @Query("select sum(d.distractionTime) from TimeSheet t join t.distractions d join t.employees e " +
            "where t.date >= :startDate and t.date <= :endDate and e.fullName = :name")
    long employeeDistractionTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                         @Param("name") String employeeName);

    @Query("select sum(r.restTime) from TimeSheet t join t.rests r join t.employees e " +
            "where t.date >= :startDate and t.date <= :endDate and e.fullName = :name")
    long employeeRestTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                  @Param("name") String employeeName);

    @Query("select sum(r.lunchTime) from TimeSheet t join t.rests r join t.employees e " +
            "where t.date >= :startDate and t.date <= :endDate and e.fullName = :name")
    long employeeLunchTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                   @Param("name") String employeeName);
}
