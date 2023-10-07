package ru.egartech.tmsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import ru.egartech.tmsystem.model.dto.DepartmentDto;
import ru.egartech.tmsystem.service.DepartmentService;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/department")
public class DepartmentController {

    private final DepartmentService departmentService;


    @PostMapping()
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto dto) {
        return ResponseEntity.ok(departmentService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto dto, @PathVariable Long id
    ) {
        return ResponseEntity.ok(departmentService.updateById(Long.valueOf(id), dto));
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@RequestParam Long id) {
        departmentService.deleteById(id);
    }
}
