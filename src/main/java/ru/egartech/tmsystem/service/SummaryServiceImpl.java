package ru.egartech.tmsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egartech.tmsystem.model.repository.SummaryRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService{

    private final SummaryRepository summaryRepository;
    @Override
    public long summaryWorkTimeByDate(LocalDate startDate, LocalDate endDate) {
        return summaryRepository.summaryWorkTimeByPeriod(startDate, endDate)
                .orElse(0L);
    }

    @Override
    public long summaryDistractionTimeByDate(LocalDate startDate, LocalDate endDate) {
        return summaryRepository.summaryDistractionTimeByPeriod(startDate, endDate)
                .orElse(0L);
    }

    @Override
    public long summaryRestTimeByDate(LocalDate startDate, LocalDate endDate) {
        return summaryRepository.summaryRestTimeByPeriod(startDate, endDate)
                .orElse(0L);
    }

}
