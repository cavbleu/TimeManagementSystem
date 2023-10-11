package ru.egartech.tmsystem.service;

import org.springframework.data.repository.query.Param;
import ru.egartech.tmsystem.model.dto.PrivilegeDto;

import java.util.List;
import java.util.Optional;

public interface PrivilegeService  {
    List<PrivilegeDto> findAll();
    Optional<PrivilegeDto> findById(Long id);
    PrivilegeDto updateById(Long id, PrivilegeDto dto);
    PrivilegeDto increasedAmountByPrivilege(@Param("name") String privilegeName);
}
