package ru.egartech.tmsystem.exception;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException() {
        super();
    }

    public EmployeeNotFoundException(Long id) {
        super(String.format("Работник с id \"%d\" удален или не было добавлен", id));
    }

    public EmployeeNotFoundException(String name) {
        super(String.format("Работник с именем \"%s\" удален или не было добавлен", name));
    }
}
