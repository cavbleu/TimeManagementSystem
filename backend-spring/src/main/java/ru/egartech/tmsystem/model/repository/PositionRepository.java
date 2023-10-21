package ru.egartech.tmsystem.model.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.egartech.tmsystem.model.entity.Position;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, Long> {
    @Query("select p " +
            "from Position p " +
            "left join fetch p.employees " +
            "where p.id = :id")
    @Override
    @NonNull
    Optional<Position> findById(@NonNull @Param("id") Long id);

}
