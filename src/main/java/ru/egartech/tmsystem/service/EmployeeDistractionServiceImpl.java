package ru.egartech.tmsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.model.dto.EmployeeDistractionDto;
import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.model.dto.FilterDto;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.model.repository.DistractionRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class EmployeeDistractionServiceImpl implements EmployeeDistractionService{

    private final DistractionRepository distractionRepository;

    @Override
    public List<EmployeeDistractionDto> employeeDistractionsByPeriod(LocalDate startDate, LocalDate endDate) {
        return distractionRepository.employeeDistractionsByPeriod(startDate, endDate);
    }

}
