package ru.egartech.tmsystem.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.time.YearMonth;
import java.util.Optional;

@Repository
public interface DeviationRepository extends EmployeeRepository {

    @Query("select sum(case when t.startWork > :startWork then 1 else 0 end) " +
            "from TimeSheet t join t.employees e  " +
            "where cast(t.date as string) like ':month%' " +
            "and e.id = :id")
    Optional<Long> employeeLateCountByMonth(@Param("startWork") LocalTime defaultStartWork, @Param("id") Long id,
                                            @Param("month") YearMonth yearMonth);

    @Query("select sum(case when t.workTime < :defaultWorkTime then 1 else 0 end) " +
            "from TimeSheet t join t.employees e  " +
            "where cast(t.date as string) like ':month%' " +
            "and e.id = :id")
    Optional<Long> earlyLeavingCountByEmployeeAndPeriod(@Param("defaultWorkTime") long defaultWorkTime, @Param("id") Long id,
                                                        @Param("month") YearMonth yearMonth);

    @Query("select sum(case when t.absenceReason != null then 1 else 0 end) " +
            "from TimeSheet t join t.employees e  " +
            "where cast(t.date as string) like ':month%' " +
            "and e.id = :id")
    Optional<Long> absenceCountByEmployeeAndPeriod(@Param("id") Long id, @Param("month") YearMonth yearMonth);

    @Query("select sum(case when t.absenceReason = null then 1 else 0 end) " +
            "from TimeSheet t join t.employees e  " +
            "where cast(t.date as string) like ':month%' " +
            "and e.id = :id")
    Optional<Long> skipCountByEmployeeAndPeriod(@Param("id") Long id, @Param("month") YearMonth yearMonth);

    @Query("select sum(case when d.distractionTime > :maxDistractionTimeByDay then 1 else 0 end) " +
            "from TimeSheet t join t.employees e join t.distractions d " +
            "where cast(t.date as string) like ':month%' " +
            "and e.id = :id")
    Optional<Long> excessDistractionTimeCountByEmployeeAndPeriod(@Param("id") Long id, @Param("month") YearMonth yearMonth,
                                                                 @Param("maxDistractionTimeByDay") long maxDistractionTimeByDay);

    @Query("select sum(case when r.restTime > :maxRestTimeByDay then 1 else 0 end) " +
            "from TimeSheet t join t.employees e join t.rests r " +
            "where cast(t.date as string) like ':month%' " +
            "and e.id = :id")
    Optional<Long> excessRestTimeCountByEmployeeAndPeriod(@Param("id") Long id, @Param("month") YearMonth yearMonth,
                                                          @Param("maxRestTimeByDay") long maxRestTimeByDay);

}
