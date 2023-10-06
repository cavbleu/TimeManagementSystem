package ru.egartech.tmsystem.service;

import org.springframework.data.repository.query.Param;
import ru.egartech.tmsystem.model.dto.DeviationDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

public interface DeviationService {

    DeviationDto deviationEmployeeByMonth(LocalDate yearMonth, Long employeeId);
    List<DeviationDto> deviationAllEmployeesByMonth(LocalDate yearMonth);

    long employeeLateCountByMonth(LocalTime defaultStartWork, Long id, YearMonth yearMonth);

    long earlyLeavingCountByEmployeeAndPeriod(long defaultWorkTime, @Param("id") Long id, YearMonth yearMonth);

    long absenceCountByEmployeeAndPeriod(Long id, YearMonth yearMonth);

    long skipCountByEmployeeAndPeriod(Long id, YearMonth yearMonth);

    long excessDistractionTimeCountByEmployeeAndPeriod(Long id, YearMonth yearMonth, long maxDistractionTimeByDay);

    long excessRestTimeCountByEmployeeAndPeriod(Long id, YearMonth yearMonth, long maxRestTimeByDay);

}
