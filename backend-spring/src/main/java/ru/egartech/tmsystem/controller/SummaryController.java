package ru.egartech.tmsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.egartech.tmsystem.model.dto.DepartmentSummaryDto;
import ru.egartech.tmsystem.model.dto.EmployeeSummaryDto;
import ru.egartech.tmsystem.model.dto.FilterDto;
import ru.egartech.tmsystem.model.dto.PositionSummaryDto;
import ru.egartech.tmsystem.service.DepartmentService;
import ru.egartech.tmsystem.service.EmployeeService;
import ru.egartech.tmsystem.service.PositionService;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("api/v1/summary")
public class SummaryController {

    private final DepartmentService departmentService;
    private final PositionService positionService;
    private final EmployeeService employeeService;

    @PutMapping("/departmentByPeriod")
    public ResponseEntity<List<DepartmentSummaryDto>> getDepartmentsSummary(@RequestBody FilterDto filterDto) {
        return ResponseEntity.ok(departmentService.departmentsSummary(filterDto.getStartDate(), filterDto.getEndDate()));
    }

    @PutMapping("/positionByPeriod")
    public ResponseEntity<List<PositionSummaryDto>> getPositionsSummary(@RequestBody FilterDto filterDto) {
        return ResponseEntity.ok(positionService.positionsSummaryByPeriod(filterDto.getStartDate(), filterDto.getEndDate()));
    }

    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeSummaryDto>> getEmployeesSummary(@RequestParam LocalDate startDate,
                                                                        @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(employeeService.employeesSummaryByPeriod(startDate, endDate));
    }

}
