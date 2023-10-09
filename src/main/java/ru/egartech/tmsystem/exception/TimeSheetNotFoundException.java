package ru.egartech.tmsystem.exception;

public class TimeSheetNotFoundException extends RuntimeException {

    public TimeSheetNotFoundException() {
        super("Табель рабочего времени удален или не был добавлен");
    }
}
