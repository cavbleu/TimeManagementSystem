package ru.egartech.tmsystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.egartech.tmsystem.model.entity.Privilege;

import java.util.Optional;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    @Query("select p.increasedAmount from Privilege p where p.name = :name")
    Optional<Privilege> increasedAmountByPrivilege(@Param("name") String privilegeName);
}
