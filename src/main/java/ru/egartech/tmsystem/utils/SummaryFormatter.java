package ru.egartech.tmsystem.utils;

import lombok.experimental.UtilityClass;
import ru.egartech.tmsystem.model.dto.EntityDto;
import ru.egartech.tmsystem.model.dto.SettingsDto;
import ru.egartech.tmsystem.model.dto.SummaryDto;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Calendar;

@UtilityClass
public class SummaryFormatter {

    private final String WORK_TIME = "от отработанного времени";
    private final String DEFAULT_WORK_TIME = "от нормы рабочего времени";

    public String format(long time, double percent, String percentFrom) {
        if (time < 0) {
            return String
                    .format("%d ч %02d мин (%.1f%% %s)",
                            time / 60,
                            -time % 60,
                            percent,
                            percentFrom);
        } else {
            return String
                    .format("%d ч %02d мин (%.1f%% %s)",
                            time / 60,
                            time % 60,
                            percent,
                            percentFrom);
        }
    }

    public double percentFormat(long count, long percentFrom) {
        return percentFrom == 0 ? count * 100 : (double) count * 100 / percentFrom;
    }

    public void toSummaryDto(long workTime, long distractionTime, long restTime, SummaryDto
            summaryDto, EntityDto entityDto, LocalDate startDate, LocalDate endDate, SettingsDto settings) {
        long productiveTime = workTime - distractionTime - restTime;
        long workingDays = getWorkingDays(startDate, endDate);
        long overTime = workTime - settings.getDefaultWorkTime() * workingDays;

        double workTimePercent = percentFormat(workTime, settings.getDefaultWorkTime() * workingDays);
        double productiveTimePercent = percentFormat(productiveTime, workTime);
        double distractionTimePercent = percentFormat(distractionTime, workTime);
        double restTimePercent = percentFormat(restTime, workTime);
        double overTimePercent = percentFormat(overTime, settings.getDefaultWorkTime());

        summaryDto.setDepartmentName(entityDto.getName());
        summaryDto.setWorkTime(format(workTime, workTimePercent, DEFAULT_WORK_TIME));
        summaryDto.setProductiveTime(format(productiveTime, productiveTimePercent, WORK_TIME));
        summaryDto.setDistractionTime(format(distractionTime, distractionTimePercent, WORK_TIME));
        summaryDto.setRestTime(format(restTime, restTimePercent, WORK_TIME));
        summaryDto.setOverTime(format(overTime, overTimePercent, DEFAULT_WORK_TIME));
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

        return ++workDays;
    }
}
