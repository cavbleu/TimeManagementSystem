package ru.egartech.tmsystem.exception;

public class DistractionNotFoundException extends RuntimeException{

    public DistractionNotFoundException() {
        super("Отвлечение удалено или не было добавлено");
    }

}
