package ru.egartech.tmsystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.egartech.tmsystem.model.entity.Department;

import java.time.LocalDate;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("select sum(t.workTime) " +
            "from TimeSheet t join t.employee e join e.position p join p.department d " +
            "where t.date >= :startDate " +
            "and t.date <= :endDate " +
            "and d.id = :id")
    Optional<Long> departmentWorkTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                              @Param("id") Long id);

    @Query("select sum(distr.distractionTime) " +
            "from TimeSheet t join t.distractions distr join t.employee e join e.position p join p.department d " +
            "where t.date >= :startDate " +
            "and t.date <= :endDate " +
            "and d.id = :id")
    Optional<Long> departmentDistractionTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                                     @Param("id") Long id);

    @Query("select sum(r.restTime) " +
            "from TimeSheet t join t.rests r join t.employee e join e.position p join p.department d " +
            "where t.date >= :startDate " +
            "and t.date <= :endDate " +
            "and d.id = :id")
    Optional<Long> departmentRestTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                              @Param("id") Long id);

    Optional<Department> findDepartmentByName(String name);
}
