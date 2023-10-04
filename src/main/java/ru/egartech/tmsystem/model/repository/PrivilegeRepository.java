package ru.egartech.tmsystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egartech.tmsystem.model.entity.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}
