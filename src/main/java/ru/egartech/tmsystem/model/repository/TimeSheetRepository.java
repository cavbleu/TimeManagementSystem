package ru.egartech.tmsystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egartech.tmsystem.model.entity.TimeSheet;

public interface TimeSheetRepository extends JpaRepository<TimeSheet, Long> {
//    @Query("select count(t.workTime) from TimeSheet t join t.employees e  where t.date >=: startDate and t.date <=: endDate and e.department.name =: departmentName")
//    public Optional<TimeSheet> findByPeriodGroupByDepartment(@Param("departmentName") String departmentName,
//                                                              @Param("startDate") LocalDate startDate,
//                                                              @Param("endDate") LocalDate endDate);
//
//    @Query("select count(t.workTime) from TimeSheet t join t.employees e  where t.date >=: startDate and t.date <=: endDate and e.position.name =: positionName")
//    public Optional<TimeSheet> findByPositionWithDateFilter(@Param("departmentName") String positionName,
//                                                              @Param("startDate") LocalDate startDate,
//                                                              @Param("endDate") LocalDate endDate);

//    public long countByWorkTimeAndDateAfterAndDateBefore(LocalDate startDate, LocalDate endDate);
}
