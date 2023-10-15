package ru.egartech.tmsystem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.egartech.tmsystem.model.dto.SettingsDto;
import ru.egartech.tmsystem.service.SettingsService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("api/v1/settings")
public class SettingsController {

    private final SettingsService settingsService;

    @GetMapping
    public ResponseEntity<List<SettingsDto>> getAllSettings() {
        return ResponseEntity.ok(settingsService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<SettingsDto> getSettingsById(@PathVariable Long id) {
        return ResponseEntity.ok(settingsService.findById(id));
    }

    @PostMapping
    public ResponseEntity<SettingsDto> createSettings(@Valid @RequestBody SettingsDto settingsDto) {
        return ResponseEntity.ok(settingsService.save(settingsDto));
    }

    @PutMapping
    public ResponseEntity<SettingsDto> updateSettings(@Valid @RequestBody SettingsDto settingsDto) {
        return ResponseEntity.ok(settingsService.updateById(settingsDto.getId(), settingsDto));
    }

    @DeleteMapping("/{settingsId}")
    public void deleteSettings(@PathVariable Long settingsId) {
        settingsService.deleteById(settingsId);
    }

}
