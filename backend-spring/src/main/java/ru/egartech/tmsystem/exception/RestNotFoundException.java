package ru.egartech.tmsystem.exception;

public class RestNotFoundException extends RuntimeException {

    public RestNotFoundException() {
        super("Перерыв удален или не был добавлен");
    }

}
