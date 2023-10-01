package ru.egartech.tmsystem.domain.filter.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FilterDto {
    LocalDateTime startPeriod;
    LocalDateTime endPeriod;
}
