package ru.egartech.tmsystem.exception;

public class CurrentSettingsNotFoundException extends RuntimeException{

    public CurrentSettingsNotFoundException() {
        super("Не установлен активный профиль настроек");
    }
}
