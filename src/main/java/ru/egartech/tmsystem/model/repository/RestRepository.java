package ru.egartech.tmsystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.egartech.tmsystem.model.dto.EmployeeDistractionDto;
import ru.egartech.tmsystem.model.dto.EmployeeRestDto;
import ru.egartech.tmsystem.model.entity.Rest;

import java.time.LocalDate;
import java.util.List;

public interface RestRepository extends JpaRepository<Rest, Long> {

    @Query("select e.name, e.age, e.position.name, e.department.name, t.rests  " +
            "from Employee e join e.timeSheet t " +
            "where e.id = :id " +
            "and t.date >= :startDate " +
            "and t.date <= :endDate")
    List<EmployeeRestDto> employeeRestsByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
