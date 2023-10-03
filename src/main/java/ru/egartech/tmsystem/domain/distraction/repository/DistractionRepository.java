package ru.egartech.tmsystem.domain.distraction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.egartech.tmsystem.domain.distraction.entity.Distraction;

import java.time.LocalDate;

public interface DistractionRepository extends JpaRepository<Distraction, Long> {
}
