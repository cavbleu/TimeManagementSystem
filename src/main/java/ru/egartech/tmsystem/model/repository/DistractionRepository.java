package ru.egartech.tmsystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.egartech.tmsystem.model.dto.DistractionDto;
import ru.egartech.tmsystem.model.dto.EmployeeDistractionDto;
import ru.egartech.tmsystem.model.entity.Distraction;

import java.time.LocalDate;
import java.util.List;

public interface DistractionRepository extends JpaRepository<Distraction, Long> {

    @Query("select e.name, e.age, e.position.name, d.name, t.distractions  " +
            "from Employee e join e.timeSheets t join e.position p join p.department d " +
            "where e.id = :id " +
            "and t.date >= :startDate " +
            "and t.date <= :endDate")
    List<EmployeeDistractionDto> employeeDistractionsByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
