package ru.egartech.tmsystem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.egartech.tmsystem.model.dto.DistractionDto;
import ru.egartech.tmsystem.service.DistractionService;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("api/v1/distraction")
public class DistractionController {

    private final DistractionService distractionService;

    @GetMapping("/{id}")
    public ResponseEntity<DistractionDto> getDistractionById(@PathVariable Long id) {
        return ResponseEntity.ok(distractionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<DistractionDto> saveDistraction(@Valid  @RequestBody DistractionDto dto) {
        return ResponseEntity.ok(distractionService.save(dto.getId(), dto));
    }

    @PutMapping
    public ResponseEntity<DistractionDto> updateDistraction(@Valid @RequestBody DistractionDto dto) {
        return ResponseEntity.ok(distractionService.updateById(dto.getId(), dto));
    }

    @DeleteMapping("/{id}")
    public void deleteDistraction(@PathVariable Long id) {
        distractionService.deleteById(id);
    }

}
