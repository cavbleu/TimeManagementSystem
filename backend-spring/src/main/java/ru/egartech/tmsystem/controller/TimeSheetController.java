package ru.egartech.tmsystem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.egartech.tmsystem.model.dto.TimeSheetDto;
import ru.egartech.tmsystem.service.TimeSheetService;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("api/v1/timesheet")
public class TimeSheetController {

    private final TimeSheetService timeSheetService;

    @GetMapping("/{id}")
    public ResponseEntity<TimeSheetDto> getTimeSheetById(@PathVariable Long id) {
        return ResponseEntity.ok(timeSheetService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TimeSheetDto> saveDepartment(@Valid  @RequestBody TimeSheetDto dto) {
        return ResponseEntity.ok(timeSheetService.save(dto));
    }

    @PutMapping()
    public ResponseEntity<TimeSheetDto> updateDepartment(@Valid @RequestBody TimeSheetDto dto) {
        return ResponseEntity.ok(timeSheetService.updateById(dto.getId(), dto));
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        timeSheetService.deleteById(id);
    }
}
