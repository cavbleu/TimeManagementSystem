package ru.egartech.tmsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class FilterDto {

    LocalDateTime startPeriod;
    LocalDateTime endPeriod;
    LocalDate yearMonth;

    public FilterDto(LocalDateTime startPeriod, LocalDateTime endPeriod) {
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
    }

    public FilterDto(LocalDate yearMonth) {
        this.yearMonth = yearMonth;
    }
}
