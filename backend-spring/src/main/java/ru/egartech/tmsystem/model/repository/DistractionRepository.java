package ru.egartech.tmsystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egartech.tmsystem.model.entity.Distraction;

import java.time.LocalDate;
import java.util.List;

public interface DistractionRepository extends JpaRepository<Distraction, Long> {

    List<Distraction> findByDateBetweenAndEmployee_Id(LocalDate startDate, LocalDate endDate, Long empId);
}
