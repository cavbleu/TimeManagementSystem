package ru.egartech.tmsystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.egartech.tmsystem.model.dto.EmployeeTimeSheetDto;
import ru.egartech.tmsystem.model.entity.TimeSheet;

import java.time.LocalDate;
import java.util.List;

public interface TimeSheetRepository extends JpaRepository<TimeSheet, Long> {

    @Query("select e.name, e.age, e.position.name, d.name, e.timeSheets  " +
            "from Employee e join e.timeSheets t join e.position p join p.department d " +
            "where e.id = :id " +
            "and t.date >= :startDate " +
            "and t.date <= :endDate")
    List<EmployeeTimeSheetDto> employeeTimeSheetsByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
