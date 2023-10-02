package ru.egartech.tmsystem.domain.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.egartech.tmsystem.domain.filter.dto.FilterDto;
import ru.egartech.tmsystem.domain.timesheet.entity.TimeSheet;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TimeSheetRepository extends JpaRepository<TimeSheet, Long> {
    @Query("select count(t.workTime) from TimeSheet t join t.employees e  where t.date >=: startDate and t.date <=: endDate and e.department.name =: departmentName")
    public Optional<TimeSheet> findByDepartmentWithDateFilter(@Param("departmentName") String departmentName,
                                                              @Param("startDate") LocalDate startDate,
                                                              @Param("endDate") LocalDate endDate);

    @Query("select count(t.workTime) from TimeSheet t join t.employees e  where t.date >=: startDate and t.date <=: endDate and e.position.name =: positionName")
    public Optional<TimeSheet> findByPositionWithDateFilter(@Param("departmentName") String positionName,
                                                              @Param("startDate") LocalDate startDate,
                                                              @Param("endDate") LocalDate endDate);

    public long countByWorkTimeAndDateAfterAndDateBefore(LocalDate startDate, LocalDate endDate);
}
