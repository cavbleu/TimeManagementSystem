package ru.egartech.tmsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.service.EmployeeService;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping()
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto dto) {
        return ResponseEntity.ok(employeeService.save(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto dto, @RequestParam Long id) {
        return ResponseEntity.ok(employeeService.updateById(id, dto));
    }

    @DeleteMapping("{id}")
    public void deleteEmployee(@RequestParam Long id) {
        employeeService.deleteById(id);
    }
}
