package ru.egartech.tmsystem.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.exception.CustomEntityNotFoundException;
import ru.egartech.tmsystem.model.dto.TimeSheetDto;
import ru.egartech.tmsystem.model.entity.TimeSheet;
import ru.egartech.tmsystem.model.mapping.TimeSheetMapper;
import ru.egartech.tmsystem.model.repository.TimeSheetRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class TimeSheetServiceImpl implements TimeSheetService{

    private final TimeSheetRepository repository;
    private final TimeSheetMapper mapper;

    public TimeSheetServiceImpl(@Qualifier("timeSheetRepository") TimeSheetRepository repository, TimeSheetMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<TimeSheetDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public TimeSheetDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new CustomEntityNotFoundException(id));
    }

    @Override
    public TimeSheetDto save(TimeSheetDto dto) {
        TimeSheet timeSheet = repository.save(mapper.toEntity(dto));
        return mapper.toDto(timeSheet);
    }

    @Override
    public TimeSheetDto updateById(Long id, TimeSheetDto dto) {
        return repository.findById(id)
                .map(entity -> {
                    BeanUtils.copyProperties(mapper.toEntity(dto), entity, "id");
                    return mapper.toDto(repository.save(entity));
                })
                .orElseThrow(() -> new CustomEntityNotFoundException(id));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<TimeSheetDto> findByDateBetweenAndEmployee_Id(LocalDate startDate, LocalDate endDate, Long empId) {
        return repository.findByDateBetweenAndEmployee_Id(startDate, endDate, empId).stream()
                .map(mapper::toDto)
                .toList();
    }
}
