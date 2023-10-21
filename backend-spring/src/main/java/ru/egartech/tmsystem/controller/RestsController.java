package ru.egartech.tmsystem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.egartech.tmsystem.model.dto.RestDto;
import ru.egartech.tmsystem.service.RestService;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("api/v1/rest")
public class RestsController {

    private final RestService restService;

    @GetMapping("/{id}")
    public ResponseEntity<RestDto> getDistractionById(@PathVariable Long id) {
        return ResponseEntity.ok(restService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RestDto> saveRest(@Valid @RequestBody RestDto dto) {
        return ResponseEntity.ok(restService.save(dto.getId(), dto));
    }

    @PutMapping
    public ResponseEntity<RestDto> updateRest(@Valid  @RequestBody RestDto dto ) {
        return ResponseEntity.ok(restService.updateById(dto.getId(), dto));
    }

    @DeleteMapping("/{id}")
    public void deleteRest(@PathVariable Long id) {
        restService.deleteById(id);
    }
}
