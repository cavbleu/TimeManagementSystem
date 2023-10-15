package ru.egartech.tmsystem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.egartech.tmsystem.model.dto.RestDto;
import ru.egartech.tmsystem.service.RestService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("api/v1/rest")
public class RestsController {

    private final RestService restService;

    @PutMapping
    public ResponseEntity<List<RestDto>> getAllRestsByPeriod() {
        return ResponseEntity.ok(restService.findAll());
    }

    @PostMapping("/{timeSheetId}")
    public ResponseEntity<RestDto> saveRest(@Valid @RequestBody RestDto dto, @PathVariable Long timeSheetId) {
        return ResponseEntity.ok(restService.save(timeSheetId, dto));
    }

    @PutMapping("/{restId}")
    public ResponseEntity<RestDto> updateRest(@Valid  @RequestBody RestDto dto, @RequestParam Long timeSheetId, @PathVariable Long restId  ) {
        return ResponseEntity.ok(restService.update(timeSheetId, restId, dto));
    }

    @DeleteMapping("/{id}")
    public void deleteRest(@PathVariable Long id) {
        restService.deleteById(id);
    }
}
