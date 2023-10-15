package ru.egartech.tmsystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.egartech.tmsystem.model.entity.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select sum(t.workTime) " +
            "from TimeSheet t join t.employee e " +
            "where t.date >= :startDate " +
            "and t.date <= :endDate " +
            "and e.id = :id")
    Optional<Long> employeeWorkTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                            @Param("id") Long id);

    @Query("select sum(d.distractionTime) " +
            "from Distraction d join d.employee e " +
            "where d.date >= :startDate " +
            "and d.date <= :endDate " +
            "and e.id = :id")
    Optional<Long> employeeDistractionTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                         @Param("id") Long id);

    @Query("select sum(r.restTime) " +
            "from Rest r join r.employee e " +
            "where r.date >= :startDate " +
            "and r.date <= :endDate " +
            "and e.id = :id")
    Optional<Long> employeeRestTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                  @Param("id") Long id);

    @Query("select e from Employee e join e.timeSheets t " +
            "where t.date >= :startDate " +
            "and t.date <= :endDate ")
    List<Employee> employeesByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
