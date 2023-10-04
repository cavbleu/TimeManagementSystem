package ru.egartech.tmsystem.utils;

import lombok.experimental.UtilityClass;

import java.time.Duration;

@UtilityClass
public class DeviationFormatter {

    public String format(long count, double percent) {
        return String
                .format("%d (%.1f) ",
                        count,
                        percent)
                .concat("%)");
    }

    public String format(long count) {
        return String.format("%d", count);
    }
}
