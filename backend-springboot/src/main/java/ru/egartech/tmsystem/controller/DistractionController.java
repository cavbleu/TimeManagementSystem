package ru.egartech.tmsystem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.egartech.tmsystem.model.dto.DistractionDto;
import ru.egartech.tmsystem.service.DistractionService;

@RequiredArgsConstructor
@org.springframework.web.bind.annotation.RestController
@RequestMapping("api/v1/distraction")
public class DistractionController {

    private final DistractionService distractionService;

    @PostMapping("/{timeSheetId}")
    public ResponseEntity<DistractionDto> saveDistraction(@Valid  @RequestBody DistractionDto dto, @PathVariable Long timeSheetId) {
        return ResponseEntity.ok(distractionService.save(timeSheetId, dto));
    }

    @PutMapping("/{distractionId}")
    public ResponseEntity<DistractionDto> updateDistraction(@Valid @RequestBody DistractionDto dto, @RequestParam Long timeSheetId,
                                                     @PathVariable Long distractionId) {
        return ResponseEntity.ok(distractionService.update(timeSheetId, distractionId, dto));
    }

    @DeleteMapping("/{id}")
    public void deleteDistraction(@PathVariable Long id) {
        distractionService.deleteById(id);
    }

}
