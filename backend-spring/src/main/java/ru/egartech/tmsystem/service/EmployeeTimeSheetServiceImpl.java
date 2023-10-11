package ru.egartech.tmsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.model.dto.EmployeeTimeSheetDto;
import ru.egartech.tmsystem.model.repository.TimeSheetRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeTimeSheetServiceImpl implements EmployeeTimeSheetService{

    private final TimeSheetRepository timeSheetRepository;


    @Override
    public List<EmployeeTimeSheetDto> employeeTimeSheetsByPeriod(LocalDate startDate, LocalDate endDate) {
        return timeSheetRepository.employeeTimeSheetsByPeriod(startDate, endDate);
    }
}
