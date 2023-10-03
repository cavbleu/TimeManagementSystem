package ru.egartech.tmsystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.egartech.tmsystem.model.entity.Position;

import java.time.LocalDate;

public interface PositionRepository extends JpaRepository<Position, Long> {

    @Query("select sum(t.workTime) from TimeSheet t join t.employees e where t.date >= :startDate " +
            "and t.date <= :endDate and e.position.name = :name")
    long positionWorkTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                    @Param("name") String positionName);

    @Query("select sum(d.distractionTime) from TimeSheet t join t.distractions d join t.employees e " +
            "where t.date >= :startDate and t.date <= :endDate and e.position.name = :name")
    long positionDistractionTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                  @Param("name") String positionName);

    @Query("select sum(r.restTime) from TimeSheet t join t.rests r join t.employees e " +
            "where t.date >= :startDate and t.date <= :endDate and e.position.name = :name")
    long positionRestTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                  @Param("name") String positionName);

    @Query("select sum(r.lunchTime) from TimeSheet t join t.rests r join t.employees e " +
            "where t.date >= :startDate and t.date <= :endDate and e.position.name = :name")
    long positionLunchTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                  @Param("name") String positionName);
}
