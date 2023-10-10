package ru.egartech.tmsystem.service;

import java.time.LocalDate;

public interface SummaryService {

    long summaryWorkTimeByDate(LocalDate startDate, LocalDate endDate);

    long summaryDistractionTimeByDate(LocalDate startDate, LocalDate endDate);

    long summaryRestTimeByDate(LocalDate startDate, LocalDate endDate);
}
