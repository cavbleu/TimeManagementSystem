package ru.egartech.tmsystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.egartech.tmsystem.model.entity.Position;

import java.time.LocalDate;
import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, Long> {

    @Query("select sum(t.workTime) from TimeSheet t join t.employee e where t.date >= :startDate " +
            "and t.date <= :endDate and e.position.id = :id")
    Optional<Long> positionWorkTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                      @Param("id") Long id);

    @Query("select sum(d.distractionTime) from Distraction d join d.employee e " +
            "where d.date >= :startDate and d.date <= :endDate and e.position.id = :id")
    Optional<Long>  positionDistractionTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                  @Param("id") Long id);

    @Query("select sum(r.restTime) from Rest r join r.employee e " +
            "where r.date >= :startDate and r.date <= :endDate and e.position.id = :id")
    Optional<Long>  positionRestTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                  @Param("id") Long id);

    Optional<Position> findByName(String positionName);
}
