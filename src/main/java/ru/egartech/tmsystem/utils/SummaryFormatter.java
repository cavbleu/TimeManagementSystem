package ru.egartech.tmsystem.utils;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;
import ru.egartech.tmsystem.model.dto.EntityDto;
import ru.egartech.tmsystem.model.dto.FilterDto;
import ru.egartech.tmsystem.model.dto.SettingsDto;
import ru.egartech.tmsystem.model.dto.SummaryDto;

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

    public void toSummaryDto(long workTime, long distractionTime, long restTime, long lunchTime,
                             SummaryDto summaryDto, EntityDto entityDto, FilterDto filter, SettingsDto settings) {
        long productiveTime = workTime - distractionTime - restTime - lunchTime;
        long days = Duration.between(filter.getStartPeriod(), filter.getEndPeriod()).toDays();
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
        summaryDto.setWorkTime(statisticFormat(workTime, workTimePercent));
        summaryDto.setProductiveTime(statisticFormat(productiveTime, productiveTimePercent));
        summaryDto.setDistractionTime(statisticFormat(distractionTime, distractionTimePercent));
        summaryDto.setRestTime(statisticFormat(restTime, restTimePercent));
        summaryDto.setLunchTime(statisticFormat(lunchTime, lunchTimePercent));
        summaryDto.setOverTime(statisticFormat(overTime, overTimeMinutes, overTimePercent));
    }
}
