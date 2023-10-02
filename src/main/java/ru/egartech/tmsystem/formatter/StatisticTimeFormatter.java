package ru.egartech.tmsystem.formatter;

import lombok.RequiredArgsConstructor;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.Duration;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class StatisticTimeFormatter {

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
}
