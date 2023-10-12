package ru.egartech.tmsystem.exception;

public class PositionConstraintException extends RuntimeException{

    public PositionConstraintException() {
        super("Нельзя удалить должность, к которой относятся сотрудники");
    }
}
