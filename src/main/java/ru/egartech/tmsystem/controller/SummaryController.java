package ru.egartech.tmsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.egartech.tmsystem.model.dto.DeviationDto;
import ru.egartech.tmsystem.model.dto.FilterDto;
import ru.egartech.tmsystem.service.DeviationService;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/summary")
public class SummaryController {

    private final DeviationService deviationService;

    @GetMapping
    public DeviationDto hello() {

        FilterDto filterDto = new FilterDto(LocalDate.of(2023, 10, 1));

        return deviationService.deviationEmployeeByMonth(filterDto, 1L);
    }
}
