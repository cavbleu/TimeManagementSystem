package ru.egartech.tmsystem.utils;

import lombok.experimental.UtilityClass;
import ru.egartech.tmsystem.model.dto.EntityDto;
import ru.egartech.tmsystem.model.dto.FilterDto;
import ru.egartech.tmsystem.model.dto.SettingsDto;
import ru.egartech.tmsystem.model.dto.SummaryDto;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Calendar;

@UtilityClass
public class SummaryFormatter {

    public String format(long time, double percent) {
        return String
                .format("%d ч %02d мин (%.1f ",
                        Duration.ofMinutes(time).toHoursPart(),
                        Duration.ofMinutes(time).toMinutesPart(),
                        percent)
                .concat("%)");
    }

    public String format(long hoursPart, long minutesPart, double percent) {
        return String
                .format("%d ч %02d мин (%.1f ",
                        Duration.ofMinutes(hoursPart).toHoursPart(),
                        Duration.ofMinutes(minutesPart).toMinutesPart(),
                        percent)
                .concat("%)");
    }

    public double percentFormat(long minutes, long percentFrom) {
        return (double) minutes * 100 / percentFrom;
    }

    public void toSummaryDto(long workTime, long distractionTime, long restTime, long lunchTime,
                             SummaryDto summaryDto, EntityDto entityDto, LocalDate startDate, LocalDate endDate, SettingsDto settings) {
        long productiveTime = workTime - distractionTime - restTime - lunchTime;
        long days = getWorkingDays(startDate, endDate);
        long overTime = workTime - settings.getDefaultWorkTime() * days;
        long overTimeMinutes = overTime > 0 ? overTime : -overTime;

        double workTimePercent = percentFormat(workTime, settings.getDefaultWorkTime() * days);
        double productiveTimePercent = percentFormat(productiveTime, workTime);
        double distractionTimePercent = percentFormat(distractionTime, workTime);
        double restTimePercent = percentFormat(restTime, workTime);
        double lunchTimePercent = percentFormat(lunchTime, workTime);
        double overTimePercent = percentFormat(overTime, settings.getDefaultWorkTime() * days);
        overTimePercent = overTimePercent > 0 ? overTimePercent : -overTime;

        summaryDto.setId(entityDto.getId());
        summaryDto.setName(entityDto.getName());
        summaryDto.setWorkTime(format(workTime, workTimePercent));
        summaryDto.setProductiveTime(format(productiveTime, productiveTimePercent));
        summaryDto.setDistractionTime(format(distractionTime, distractionTimePercent));
        summaryDto.setRestTime(format(restTime, restTimePercent));
        summaryDto.setLunchTime(format(lunchTime, lunchTimePercent));
        summaryDto.setOverTime(format(overTime, overTimeMinutes, overTimePercent));
    }

    public static long getWorkingDays(LocalDate startDate, LocalDate endDate) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(Date.valueOf(startDate));

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(Date.valueOf(endDate));

        long workDays = 0;

        if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
            return 0;
        }

        if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
            startCal.setTime(Date.valueOf(endDate));
            endCal.setTime(Date.valueOf(startDate));
        }

        do {
            startCal.add(Calendar.DAY_OF_MONTH, 1);
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                ++workDays;
            }
        } while (startCal.getTimeInMillis() < endCal.getTimeInMillis());

        return workDays;
    }
}
