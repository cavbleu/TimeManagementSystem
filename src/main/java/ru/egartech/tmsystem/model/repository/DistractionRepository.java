package ru.egartech.tmsystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egartech.tmsystem.model.entity.Distraction;

public interface DistractionRepository extends JpaRepository<Distraction, Long> {
}
