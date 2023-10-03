package ru.egartech.tmsystem.domain.department.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.egartech.tmsystem.domain.department.dto.DepartmentSummaryDto;
import ru.egartech.tmsystem.domain.department.service.DepartmentService;
import ru.egartech.tmsystem.domain.filter.dto.FilterDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
public class DepartmentController {

    private final DepartmentService departmentService;

    public void getAllBreaksByDepartment(){}

    @GetMapping("/workTime")
    public Long getWorkTimeByDepartments(){
        FilterDto filter = new FilterDto(LocalDateTime.of(LocalDate.of(2023, 10, 1),
                LocalTime.of(13, 10)),
                LocalDateTime.of(LocalDate.of(2023, 10, 3),
                        LocalTime.of(15, 10)));
        LocalDate start = filter.getStartPeriod().toLocalDate();
        LocalDate end = filter.getEndPeriod().toLocalDate();
        return null;
    }

    public void getAllLunchTimeByDepartments(){}
}
