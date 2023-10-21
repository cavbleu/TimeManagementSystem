package ru.egartech.tmsystem.model.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.egartech.tmsystem.model.entity.Department;

import java.time.LocalDate;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("select sum(t.workTime) " +
            "from TimeSheet t join t.employee e join e.position p join p.department dep " +
            "where t.date >= :startDate " +
            "and t.date <= :endDate " +
            "and dep.id = :id")
    Optional<Long> departmentWorkTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                              @Param("id") Long id);

    @Query("select sum(d.distractionTime) " +
            "from Distraction d join d.employee e join e.position p join p.department dep " +
            "where d.date >= :startDate " +
            "and d.date <= :endDate " +
            "and dep.id = :id")
    Optional<Long> departmentDistractionTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                                     @Param("id") Long id);

    @Query("select sum(r.restTime) " +
            "from Rest r join r.employee e join e.position p join p.department dep " +
            "where r.date >= :startDate " +
            "and r.date <= :endDate " +
            "and dep.id = :id")
    Optional<Long> departmentRestTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                              @Param("id") Long id);

    @Query("select  d " +
            "from Department d " +
            "left join fetch d.positions " +
            "where d.id = :id"
            )
    @Override
    @NonNull
    Optional<Department> findById(@NonNull @Param("id") Long id);

}
