package ru.egartech.tmsystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egartech.tmsystem.model.entity.Rest;

import java.time.LocalDate;
import java.util.List;

public interface RestRepository extends JpaRepository<Rest, Long> {

    List<Rest> findByDateBetweenAndEmployee_Id(LocalDate startDate, LocalDate endDate, Long empId);

}
