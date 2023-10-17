package ru.egartech.tmsystem.exception;

public class ActiveProfileDeleteException extends RuntimeException{

    public ActiveProfileDeleteException() {
        super("Нельзя удалить активный профиль настроек");
    }
}
