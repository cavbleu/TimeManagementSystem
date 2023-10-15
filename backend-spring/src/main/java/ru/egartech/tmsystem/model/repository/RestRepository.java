package ru.egartech.tmsystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.egartech.tmsystem.model.dto.EmployeeRestDto;
import ru.egartech.tmsystem.model.entity.Rest;
import ru.egartech.tmsystem.model.entity.TimeSheet;

import java.time.LocalDate;
import java.util.List;

public interface RestRepository extends JpaRepository<Rest, Long> {

    @Query("select e.name, e.age, p.name, d.name  " +
            "from Employee e join e.timeSheets t join e.position p join p.department d " +
            "where e.id = :id " +
            "and t.date >= :startDate " +
            "and t.date <= :endDate")
    List<EmployeeRestDto> employeeRestsByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    List<Rest> findByDateBetweenAndEmployee_Id(LocalDate startDate, LocalDate endDate, Long empId);

}
