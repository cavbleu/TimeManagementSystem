package ru.egartech.tmsystem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.egartech.tmsystem.model.dto.PositionDto;
import ru.egartech.tmsystem.service.PositionService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("api/v1/position")
public class PositionController {

    private final PositionService positionService;

    @GetMapping()
    public ResponseEntity<List<PositionDto>> getAllPositions() {
        return ResponseEntity.ok(positionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PositionDto> getPositionById(@PathVariable Long id) {
        return ResponseEntity.ok(positionService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<PositionDto> savePosition(@Valid  @RequestBody PositionDto dto, @RequestParam String departmentName) {
        return ResponseEntity.ok(positionService.save(dto, departmentName));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PositionDto> updatePosition(@Valid @RequestBody PositionDto dto, @PathVariable Long id,
                                                      @RequestParam String departmentName) {
        return ResponseEntity.ok(positionService.update(dto, departmentName, id));
    }

    @DeleteMapping("/{id}")
    public void deletePosition(@PathVariable Long id) {
        positionService.deleteById(id);
    }
}
