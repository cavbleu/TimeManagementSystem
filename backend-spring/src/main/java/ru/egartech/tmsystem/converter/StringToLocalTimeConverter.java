package ru.egartech.tmsystem.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class StringToLocalTimeConverter implements Converter<String, LocalTime> {
    @Override
    public LocalTime convert(String s) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(s, fmt);
    }

}
