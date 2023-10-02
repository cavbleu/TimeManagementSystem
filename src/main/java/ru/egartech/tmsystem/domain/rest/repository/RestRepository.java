package ru.egartech.tmsystem.domain.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egartech.tmsystem.domain.rest.entity.Rest;

public interface RestRepository extends JpaRepository<Rest, Long> {
}
