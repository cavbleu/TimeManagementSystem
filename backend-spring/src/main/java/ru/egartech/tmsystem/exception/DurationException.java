package ru.egartech.tmsystem.exception;

public class DurationException extends RuntimeException{

    public DurationException(long period) {
        super(String.format("Период не может быть более %d дней", period));
    }
}
