package ru.egartech.tmsystem.domain.distraction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egartech.tmsystem.domain.distraction.entity.Distraction;

public interface DistractionRepository extends JpaRepository<Distraction, Long> {
}
