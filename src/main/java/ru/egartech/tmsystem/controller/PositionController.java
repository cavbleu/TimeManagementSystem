package ru.egartech.tmsystem.controller;

import jakarta.websocket.server.PathParam;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import ru.egartech.tmsystem.model.dto.DepartmentDto;
import ru.egartech.tmsystem.model.dto.PositionDto;
import ru.egartech.tmsystem.service.DepartmentService;
import ru.egartech.tmsystem.service.PositionService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/position")
public class PositionController {

    private final PositionService positionService;

    @GetMapping()
    public ResponseEntity<List<PositionDto>> getAllPositions() {
        return ResponseEntity.ok(positionService.findAll());
    }

    @PostMapping()
    public ResponseEntity<PositionDto> savePosition(@RequestBody PositionDto dto, @RequestParam String departmentName) {
        return ResponseEntity.ok(positionService.save(dto, departmentName));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PositionDto> updatePosition(@RequestBody PositionDto dto, @PathVariable Long id,
                                                      @RequestParam String departmentName) {
        return ResponseEntity.ok(positionService.update(dto, departmentName, id));
    }

    @DeleteMapping("/{id}")
    public void deletePosition(@PathVariable Long id) {
        positionService.deleteById(id);
    }
}
