package ru.egartech.tmsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.egartech.tmsystem.model.dto.RestDto;
import ru.egartech.tmsystem.service.RestService;

@RequiredArgsConstructor
@org.springframework.web.bind.annotation.RestController
@RequestMapping("api/v1/rest")
public class RestController {

    private final RestService restService;

    @PostMapping("/{id}")
    public ResponseEntity<RestDto> saveDepartment(@RequestBody RestDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(restService.save(id, dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestDto> updateDepartment(@RequestBody RestDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(restService.updateById(id, dto));
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        restService.deleteById(id);
    }
}
