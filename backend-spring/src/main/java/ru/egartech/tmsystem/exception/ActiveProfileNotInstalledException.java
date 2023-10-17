package ru.egartech.tmsystem.exception;

public class ActiveProfileNotInstalledException extends RuntimeException{

    public ActiveProfileNotInstalledException() {
        super("Не установлен активный профиль настроек");
    }
}
