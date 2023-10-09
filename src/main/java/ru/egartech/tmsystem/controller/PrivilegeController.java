package ru.egartech.tmsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import ru.egartech.tmsystem.model.dto.PrivilegeDto;
import ru.egartech.tmsystem.service.PrivilegeService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/privilege")
public class PrivilegeController {

    private final PrivilegeService privilegeService;

    @GetMapping
    public ResponseEntity<List<PrivilegeDto>> getAllPrivileges() {
        return ResponseEntity.ok(privilegeService.findAll());
    }

    @PutMapping("/{privilegeId}")
    public ResponseEntity<PrivilegeDto> updatePrivilege(@RequestBody PrivilegeDto privilegeDto, @PathVariable Long privilegeId) {
        return ResponseEntity.ok(privilegeService.updateById(privilegeId, privilegeDto));
    }

}
