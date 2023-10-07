package ru.egartech.tmsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.dto.PositionDto;
import ru.egartech.tmsystem.service.EmployeeService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PostMapping()
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto dto, @RequestParam String departmentName,
                                                    @RequestParam String positionName) {
        return ResponseEntity.ok(employeeService.save(dto, positionName, departmentName));
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto dto, @PathVariable Long id,
                                                      @RequestParam String departmentName, @RequestParam String positionName) {
        return ResponseEntity.ok(employeeService.update(id, dto, positionName, departmentName));
    }

    @DeleteMapping("{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
    }
}
