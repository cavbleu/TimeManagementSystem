package ru.egartech.tmsystem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.egartech.tmsystem.model.dto.PrivilegeDto;
import ru.egartech.tmsystem.service.PrivilegeService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("api/v1/privilege")
public class PrivilegeController {

    private final PrivilegeService privilegeService;

    @GetMapping
    public ResponseEntity<List<PrivilegeDto>> getAllPrivileges() {
        return ResponseEntity.ok(privilegeService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<PrivilegeDto> getPrivilegeById(@PathVariable Long id) {
        return ResponseEntity.ok(privilegeService.findById(id));
    }

    @PutMapping
    public ResponseEntity<PrivilegeDto> updatePrivilege(@Valid  @RequestBody PrivilegeDto privilegeDto) {
        return ResponseEntity.ok(privilegeService.updateById(privilegeDto.getId(), privilegeDto));
    }

}
