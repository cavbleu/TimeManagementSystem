package ru.egartech.tmsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.model.dto.EmployeeRestDto;
import ru.egartech.tmsystem.model.dto.FilterDto;
import ru.egartech.tmsystem.model.repository.RestRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeRestServiceImpl implements EmployeeRestService{

    private final RestRepository restRepository;
    @Override
    public List<EmployeeRestDto> employeeRestsByPeriod(FilterDto filter) {
        return restRepository.employeeRestsByPeriod(filter.getStartPeriod().toLocalDate(),
                filter.getEndPeriod().toLocalDate());
    }
}
