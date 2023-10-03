package ru.egartech.tmsystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egartech.tmsystem.model.entity.Rest;

public interface RestRepository extends JpaRepository<Rest, Long> {
}
