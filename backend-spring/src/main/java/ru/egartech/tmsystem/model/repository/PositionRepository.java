package ru.egartech.tmsystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egartech.tmsystem.model.entity.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {

}
