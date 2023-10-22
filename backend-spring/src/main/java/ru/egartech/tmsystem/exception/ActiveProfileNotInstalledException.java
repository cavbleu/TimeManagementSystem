package ru.egartech.tmsystem.exception;

public class ActiveProfileNotInstalledException extends RuntimeException{

    public ActiveProfileNotInstalledException() {
        super("Должен быть установлен 1 активный профиль настроек");
    }
}
