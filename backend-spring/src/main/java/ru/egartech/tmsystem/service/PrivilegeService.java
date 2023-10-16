package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.PrivilegeDto;

import java.util.List;

public interface PrivilegeService  {
    List<PrivilegeDto> findAll();
    PrivilegeDto findById(Long id);
    PrivilegeDto updateById(Long id, PrivilegeDto dto);
}
