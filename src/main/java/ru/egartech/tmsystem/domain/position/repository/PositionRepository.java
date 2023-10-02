package ru.egartech.tmsystem.domain.position.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egartech.tmsystem.domain.position.entity.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
