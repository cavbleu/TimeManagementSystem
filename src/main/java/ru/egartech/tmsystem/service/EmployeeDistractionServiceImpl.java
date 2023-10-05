package ru.egartech.tmsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.model.dto.EmployeeDistractionDto;
import ru.egartech.tmsystem.model.dto.FilterDto;

import java.util.List;
@RequiredArgsConstructor
@Service
public class EmployeeDistractionServiceImpl implements EmployeeDistractionService{

    private final EmployeeService employeeService;
    private final DistractionService distractionService;

    @Override
    public List<EmployeeDistractionDto> empDistractionsByPeriod(FilterDto filter) {



        return null;
    }

    @Override
    public EmployeeDistractionDto empDistractionByPeriod(FilterDto filter) {
        return null;
    }
}
