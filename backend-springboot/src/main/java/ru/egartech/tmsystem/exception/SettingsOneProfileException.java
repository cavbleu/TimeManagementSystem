package ru.egartech.tmsystem.exception;

public class SettingsOneProfileException extends RuntimeException{

    public SettingsOneProfileException() {
        super("Нельзя удалить профиль настроек если нет других доступных профилей");
    }
}
