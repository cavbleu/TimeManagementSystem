package ru.egartech.tmsystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.egartech.tmsystem.model.entity.Employee;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select sum(t.workTime) " +
            "from TimeSheet t join t.employees e " +
            "where t.date >= :startDate " +
            "and t.date <= :endDate and e.id = :id")
    long employeeWorkTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                  @Param("id") Long id);

    @Query("select sum(d.distractionTime) " +
            "from TimeSheet t join t.distractions d join t.employees e " +
            "where t.date >= :startDate " +
            "and t.date <= :endDate " +
            "and e.id = :id")
    long employeeDistractionTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                         @Param("id") Long id);

    @Query("select sum(r.restTime) " +
            "from TimeSheet t join t.rests r join t.employees e " +
            "where t.date >= :startDate " +
            "and t.date <= :endDate " +
            "and e.id = :id")
    long employeeRestTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                  @Param("id") Long id);

    @Query("select sum(r.lunchTime) " +
            "from TimeSheet t join t.rests r join t.employees e " +
            "where t.date >= :startDate " +
            "and t.date <= :endDate " +
            "and e.id = :id")
    long employeeLunchTimeByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                   @Param("id") Long id);

    @Query("select sum(case when t.startWork > :startWork then 1 else 0 end) " +
            "from TimeSheet t join t.employees e  " +
            "where cast(t.date as string) like ':month%' " +
            "and e.id = :id")
    long employeeLateCountByMonth(@Param("startWork") LocalTime defaultStartWork, @Param("id") Long id,
                                  @Param("month") YearMonth yearMonth);

    @Query("select sum(case when t.workTime < :defaultWorkTime then 1 else 0 end) " +
            "from TimeSheet t join t.employees e  " +
            "where cast(t.date as string) like ':month%' " +
            "and e.id = :id")
    public long earlyLeavingCountByEmployeeAndPeriod(@Param("defaultWorkTime") long defaultWorkTime, @Param("id") Long id,
                                                     @Param("month") YearMonth yearMonth);

    @Query("select sum(case when t.absenceReason != null then 1 else 0 end) " +
            "from TimeSheet t join t.employees e  " +
            "where cast(t.date as string) like ':month%' " +
            "and e.id = :id")
    public long absenceCountByEmployeeAndPeriod(@Param("id") Long id, @Param("month") YearMonth yearMonth);

    @Query("select sum(case when t.absenceReason = null then 1 else 0 end) " +
            "from TimeSheet t join t.employees e  " +
            "where cast(t.date as string) like ':month%' " +
            "and e.id = :id")
    public long skipCountByEmployeeAndPeriod(@Param("id") Long id, @Param("month") YearMonth yearMonth);

    @Query("select sum(case when d.distractionTime > :maxDistractionTimeByDay then 1 else 0 end) " +
            "from TimeSheet t join t.employees e join t.distractions d " +
            "where cast(t.date as string) like ':month%' " +
            "and e.id = :id")
    public long excessDistractionTimeCountByEmployeeAndPeriod(@Param("id") Long id, @Param("month") YearMonth yearMonth,
                                                              @Param("maxDistractionTimeByDay") long maxDistractionTimeByDay);

    @Query("select sum(case when r.restTime > :maxRestTimeByDay then 1 else 0 end) " +
            "from TimeSheet t join t.employees e join t.rests r " +
            "where cast(t.date as string) like ':month%' " +
            "and e.id = :id")
    public long excessRestTimeCountByEmployeeAndPeriod(@Param("id") Long id, @Param("month") YearMonth yearMonth,
                                                       @Param("maxRestTimeByDay") long maxRestTimeByDay);

    @Query("select sum(case when r.lunchTime > :maxLunchTimeByDay then 1 else 0 end) " +
            "from TimeSheet t join t.employees e join t.rests r " +
            "where cast(t.date as string) like ':month%' " +
            "and e.id = :id")
    public long excessLunchTimeCountByEmployeeAndPeriod(@Param("id") Long id, @Param("month") YearMonth yearMonth,
                                                        @Param("maxLunchTimeByDay") long maxLunchTimeByDay);

}
