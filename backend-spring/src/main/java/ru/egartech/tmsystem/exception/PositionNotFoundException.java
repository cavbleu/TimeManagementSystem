package ru.egartech.tmsystem.exception;

public class PositionNotFoundException extends RuntimeException{

    public PositionNotFoundException() {
        super();
    }

    public PositionNotFoundException(Long id) {
        super(String.format("Должность с id \"%d\" удалена или не была добавлена", id));
    }

    public PositionNotFoundException(String name) {
        super(String.format("Должность с наименованием \"%s\" удалена или не была добавлена", name));
    }
}
