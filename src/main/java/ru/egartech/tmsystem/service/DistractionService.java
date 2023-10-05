package ru.egartech.tmsystem.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.egartech.tmsystem.model.dto.DistractionDto;
import ru.egartech.tmsystem.model.dto.EmployeeDistractionDto;
import ru.egartech.tmsystem.model.dto.FilterDto;
import ru.egartech.tmsystem.utils.BaseService;

import java.time.LocalDate;
import java.util.List;

public interface DistractionService extends BaseService<DistractionDto, Long> {

}
