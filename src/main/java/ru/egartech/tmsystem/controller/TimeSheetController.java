package ru.egartech.tmsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import ru.egartech.tmsystem.model.dto.TimeSheetDto;
import ru.egartech.tmsystem.service.TimeSheetService;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/timesheet")
public class TimeSheetController {

    private final TimeSheetService timeSheetService;

    @PostMapping
    public ResponseEntity<TimeSheetDto> saveDepartment(@RequestBody TimeSheetDto dto) {
        return ResponseEntity.ok(timeSheetService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimeSheetDto> updateDepartment(@RequestBody TimeSheetDto dto, @PathVariable Long id
    ) {
        return ResponseEntity.ok(timeSheetService.updateById(id, dto));
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        timeSheetService.deleteById(id);
    }
}
