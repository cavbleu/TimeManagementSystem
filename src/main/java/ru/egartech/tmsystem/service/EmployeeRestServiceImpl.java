package ru.egartech.tmsystem.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.model.dto.EmployeeRestDto;
import ru.egartech.tmsystem.model.repository.RestRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeRestServiceImpl implements EmployeeRestService{

    private final RestRepository restRepository;
    @Override
    public List<EmployeeRestDto> employeeRestsByPeriod(LocalDate startDate, LocalDate endDate) {
        return restRepository.employeeRestsByPeriod(startDate, endDate);
    }
}
