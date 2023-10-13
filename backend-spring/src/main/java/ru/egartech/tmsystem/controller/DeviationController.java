package ru.egartech.tmsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.egartech.tmsystem.model.dto.DeviationDto;
import ru.egartech.tmsystem.model.dto.FilterDto;
import ru.egartech.tmsystem.service.DeviationService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("api/v1/deviation")
public class DeviationController {

    private final DeviationService deviationService;

    @PutMapping
    public ResponseEntity<List<DeviationDto>> getDeviationsByEmployee(@RequestBody FilterDto filterDto) {
        return ResponseEntity.ok(deviationService.deviationAllEmployeesByMonth(filterDto.getYearMonth()));
    }

}
