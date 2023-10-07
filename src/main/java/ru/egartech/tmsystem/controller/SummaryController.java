package ru.egartech.tmsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import ru.egartech.tmsystem.model.dto.*;
import ru.egartech.tmsystem.service.DepartmentService;
import ru.egartech.tmsystem.service.EmployeeService;
import ru.egartech.tmsystem.service.PositionService;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/summary")
public class SummaryController {

    private final DepartmentService departmentService;
    private final PositionService positionService;
    private final EmployeeService employeeService;

    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentSummaryDto>> getDepartmentsSummary(@RequestParam LocalDate startDate,
                                                                            @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(departmentService.departmentsSummary(startDate, endDate));
    }

    @PostMapping("/departments")
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.ok(departmentService.save(departmentDto));
    }

    @PutMapping("/departments")
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto departmentDto, @RequestParam Long id) {
        return ResponseEntity.ok(departmentService.updateById(id, departmentDto));
    }

    @DeleteMapping("/departments")
    public void deleteDepartment(@RequestBody DepartmentDto departmentDto, @RequestParam Long id) {
        departmentService.deleteById(id);
    }


    @GetMapping("/positions")
    public ResponseEntity<List<PositionSummaryDto>> getPositionsSummary(@RequestParam LocalDate startDate,
                                                                        @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(positionService.positionsSummaryByPeriod(startDate, endDate));
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeSummaryDto>> getEmployeesSummary(@RequestParam LocalDate startDate,
                                                                        @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(employeeService.employeesSummaryByPeriod(startDate, endDate));
    }

}
