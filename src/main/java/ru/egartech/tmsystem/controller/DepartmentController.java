package ru.egartech.tmsystem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import ru.egartech.tmsystem.model.dto.DepartmentDto;
import ru.egartech.tmsystem.service.DepartmentService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping()
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.findAll());
    }

    @PostMapping()
    public ResponseEntity<DepartmentDto> saveDepartment(@Valid @RequestBody DepartmentDto dto) {
        return ResponseEntity.ok(departmentService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@Valid @RequestBody DepartmentDto dto, @PathVariable Long id
    ) {
        return ResponseEntity.ok(departmentService.updateById(id, dto));
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteById(id);
    }
}
