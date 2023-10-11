package ru.egartech.tmsystem.exception;

public class SettingsNotFoundException extends RuntimeException {

    public SettingsNotFoundException() {
        super();
    }

    public SettingsNotFoundException(Long id) {
        super(String.format("Профиль настроек с id \"%d\" удален или не был добавлен", id));
    }

    public SettingsNotFoundException(String name) {
        super(String.format("Профиль настроек с наименованием \"%s\" удален или не был добавлен", name));
    }

}
