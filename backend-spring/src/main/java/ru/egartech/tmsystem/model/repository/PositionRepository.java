package ru.egartech.tmsystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.egartech.tmsystem.model.entity.Position;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {

    @Query("select p " +
            "from Position p join fetch p.employees")
    @Override
    List<Position> findAll();
}
