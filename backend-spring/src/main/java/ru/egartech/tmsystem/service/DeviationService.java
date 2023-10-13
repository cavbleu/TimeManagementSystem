package ru.egartech.tmsystem.service;

import ru.egartech.tmsystem.model.dto.DeviationDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface DeviationService {

    DeviationDto deviationEmployeeByMonth(LocalDate yearMonth, Long employeeId);
    List<DeviationDto> deviationAllEmployeesByMonth(LocalDate yearMonth);

    long employeeLateCountByMonth(LocalTime defaultStartWork, Long id, LocalDate startDate, LocalDate endDate);

    long earlyLeavingCountByEmployeeAndPeriod(long defaultWorkTime, Long id, LocalDate startDate, LocalDate endDate);

    long absenceCountByEmployeeAndPeriod(Long id, LocalDate startDate, LocalDate endDate);

    long skipCountByEmployeeAndPeriod(Long id, LocalDate startDate, LocalDate endDate);

    long excessDistractionTimeCountByEmployeeAndPeriod(Long id, LocalDate startDate, LocalDate endDate, long maxDistractionTimeByDay);

    long excessRestTimeCountByEmployeeAndPeriod(Long id, LocalDate startDate, LocalDate endDate, long maxRestTimeByDay);

}
