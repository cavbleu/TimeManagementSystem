package ru.egartech.tmsystem.exception;

public class DepartmentConstraintException extends RuntimeException{

   public DepartmentConstraintException() {
        super("Нельзя удалить отдел, в котором присутствуют должности");
    }
}
