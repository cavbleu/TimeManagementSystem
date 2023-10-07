package ru.egartech.tmsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import ru.egartech.tmsystem.model.dto.PositionDto;
import ru.egartech.tmsystem.service.PositionService;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/position")
public class PositionController {

    private final PositionService positionService;

    @PostMapping("/position")
    public ResponseEntity<PositionDto> savePosition(@RequestBody PositionDto dto) {
        return ResponseEntity.ok(positionService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PositionDto> updatePosition(@RequestBody PositionDto dto, @RequestParam Long id) {
        return ResponseEntity.ok(positionService.updateById(id, dto));
    }

    @DeleteMapping("/{id}")
    public void deletePosition(@RequestParam Long id) {
        positionService.deleteById(id);
    }
}
