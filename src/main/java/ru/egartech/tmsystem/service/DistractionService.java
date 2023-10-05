package ru.egartech.tmsystem.service;

import org.springframework.data.jpa.repository.Query;
import ru.egartech.tmsystem.model.dto.DistractionDto;
import ru.egartech.tmsystem.utils.BaseService;

import java.time.LocalDate;
import java.util.List;

public interface DistractionService extends BaseService<DistractionDto, Long> {

    List<DistractionDto> employeeDistractionsByDate(LocalDate date, Long employeeId);
}
