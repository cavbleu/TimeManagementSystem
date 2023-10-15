package ru.egartech.tmsystem.utils;

import lombok.experimental.UtilityClass;
import ru.egartech.tmsystem.exception.DurationException;
import ru.egartech.tmsystem.exception.StartDateEarlierException;

import java.time.LocalDate;
import java.time.Period;

@UtilityClass
public class PeriodValidation {

    public void validatePeriod(int days, int months, int years, LocalDate startDate, LocalDate endDate){
        if (Period.between(startDate, endDate).getDays() > days) {
            throw new DurationException(days);
        } else if (Period.between(startDate, endDate).getMonths() > months) {
            throw new DurationException(days);
        } else if (Period.between(startDate, endDate).getYears() > years) {
            throw new DurationException(days);
        }

        if (startDate.isAfter(endDate)) {
            throw new StartDateEarlierException();
        }
    }
}
