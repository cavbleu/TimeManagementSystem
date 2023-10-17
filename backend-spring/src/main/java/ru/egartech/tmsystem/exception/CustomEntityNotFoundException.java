package ru.egartech.tmsystem.exception;

import jakarta.persistence.EntityNotFoundException;

public class CustomEntityNotFoundException extends EntityNotFoundException {

    public CustomEntityNotFoundException() {
        super("Объект удален или не был добавлен");
    }

    public CustomEntityNotFoundException(Long id) {
        super(String.format("Объект удален или не был добавлен, id: %d", id));
    }
}
