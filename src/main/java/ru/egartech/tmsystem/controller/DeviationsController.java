package ru.egartech.tmsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.egartech.tmsystem.model.dto.DeviationDto;
import ru.egartech.tmsystem.service.DeviationService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/deviation")
public class DeviationsController {

    private final DeviationService deviationService;

    @GetMapping()
    public ResponseEntity<List<DeviationDto>> getDeviationsByEmployee(@RequestParam LocalDate yearMonth) {
        return ResponseEntity.ok(deviationService.deviationAllEmployeesByMonth(yearMonth));
    }

}
