package ru.egartech.tmsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.egartech.tmsystem.model.dto.SettingsDto;
import ru.egartech.tmsystem.service.SettingsService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/settings")
public class SettingsController {

    private final SettingsService settingsService;

    @GetMapping
    public ResponseEntity<List<SettingsDto>> getAllSettings() {
        return ResponseEntity.ok(settingsService.findAll());
    }

    @PostMapping()
    public ResponseEntity<SettingsDto> createSettings(@RequestBody SettingsDto settingsDto) {
        return ResponseEntity.ok(settingsService.save(settingsDto));
    }

    @PutMapping("/{settingsId}")
    public ResponseEntity<SettingsDto> updateSettings(@RequestBody SettingsDto settingsDto, @PathVariable Long settingsId) {
        return ResponseEntity.ok(settingsService.updateById(settingsId, settingsDto));
    }

    @DeleteMapping("/{settingsId}")
    public void deleteSettings(@PathVariable Long settingsId) {
        settingsService.deleteById(settingsId);
    }

}
