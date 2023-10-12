package ru.egartech.tmsystem.exception;

public class DepartmentConstraintException extends RuntimeException{

   public DepartmentConstraintException() {
        super("Нельзя удалить отдел, к которому относятся должности");
    }
}
