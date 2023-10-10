package ru.egartech.tmsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.egartech.tmsystem.model.dto.DepartmentSummaryDto;
import ru.egartech.tmsystem.model.dto.EmployeeSummaryDto;
import ru.egartech.tmsystem.model.dto.PositionSummaryDto;
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

    @GetMapping("/department")
    public ResponseEntity<List<DepartmentSummaryDto>> getDepartmentsSummary(@RequestParam LocalDate startDate,
                                                                            @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(departmentService.departmentsSummary(startDate, endDate));
    }

    @GetMapping("/position")
    public ResponseEntity<List<PositionSummaryDto>> getPositionsSummary(@RequestParam LocalDate startDate,
                                                                        @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(positionService.positionsSummaryByPeriod(startDate, endDate));
    }


    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeSummaryDto>> getEmployeesSummary(@RequestParam LocalDate startDate,
                                                                        @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(employeeService.employeesSummaryByPeriod(startDate, endDate));
    }

}
