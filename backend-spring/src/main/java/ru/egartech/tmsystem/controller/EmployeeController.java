package ru.egartech.tmsystem.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.egartech.tmsystem.model.dto.EditEmployeeDto;
import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.dto.FilterDto;
import ru.egartech.tmsystem.service.EmployeeService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/employee")
@CrossOrigin(origins = "http://localhost:3000/")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PutMapping("/byPeriod")
    public ResponseEntity<List<EmployeeDto>> getAllEmployeesByPeriod(@RequestBody FilterDto filterDto) {
        return ResponseEntity.ok(employeeService.findAllByPeriod(filterDto.getStartDate(), filterDto.getEndDate()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditEmployeeDto> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEditEmployeeDtoById(id));
    }

    @PostMapping
    public ResponseEntity<EditEmployeeDto> saveEmployee(@Valid  @RequestBody EditEmployeeDto dto) {
        return ResponseEntity.ok(employeeService.save(dto));
    }

    @PutMapping
    public ResponseEntity<EditEmployeeDto> updateEmployee(@Valid @RequestBody EditEmployeeDto dto) {
        return ResponseEntity.ok(employeeService.update(dto));
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
    }
}
