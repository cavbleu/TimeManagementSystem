package ru.egartech.tmsystem.utils;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
@UtilityClass
public class SummaryFormatter {

    public String statisticFormat(long time, double percent) {
        return String
                .format("%d ч %02d мин (%.1f ",
                        Duration.ofMinutes(time).toHoursPart(),
                        Duration.ofMinutes(time).toMinutesPart(),
                        percent)
                .concat("%)");
    }

    public String statisticFormat(long hoursPart, long minutesPart, double percent) {
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

    public <T> T toSummaryDto( long workTime, long distractionTime, long restTime, long lunchTime, T t) {
        long productiveTime = workTime - distractionTime - restTime - lunchTime;
        long days = Duration.between(filter.getStartPeriod(), filter.getEndPeriod()).toDays();
        long overTime = workTime - settings.getDefaultWorkTime() * days;
        long overTimeMinutes = overTime > 0 ? overTime : -overTime;

        double workTimePercent = SummaryFormatter.percentFormat(workTime, settings.getDefaultWorkTime() * days);
        double productiveTimePercent = SummaryFormatter.percentFormat(productiveTime, workTime);
        double distractionTimePercent = SummaryFormatter.percentFormat(distractionTime, workTime);
        double restTimePercent = SummaryFormatter.percentFormat(restTime, workTime);
        double lunchTimePercent = SummaryFormatter.percentFormat(lunchTime, workTime);
        double overTimePercent = SummaryFormatter.percentFormat(overTime, settings.getDefaultWorkTime() * days);
        overTimePercent = overTimePercent > 0 ? overTimePercent : -overTime;

                                           employeeSummaryDto.setId(employee.getId());
        employeeSummaryDto.setName(employee.getName());
        employeeSummaryDto.setWorkTime(SummaryFormatter.statisticFormat(workTime, workTimePercent));
        employeeSummaryDto.setProductiveTime(SummaryFormatter.statisticFormat(productiveTime, productiveTimePercent));
        employeeSummaryDto.setDistractionTime(SummaryFormatter.statisticFormat(distractionTime, distractionTimePercent));
        employeeSummaryDto.setRestTime(SummaryFormatter.statisticFormat(restTime, restTimePercent));
        employeeSummaryDto.setLunchTime(SummaryFormatter.statisticFormat(lunchTime, lunchTimePercent));
        employeeSummaryDto.setOverTime(SummaryFormatter.statisticFormat(overTime, overTimeMinutes, overTimePercent));
    }
}
