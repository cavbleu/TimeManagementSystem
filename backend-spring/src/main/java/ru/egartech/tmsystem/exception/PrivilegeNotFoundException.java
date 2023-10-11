package ru.egartech.tmsystem.exception;

public class PrivilegeNotFoundException extends RuntimeException {

    public PrivilegeNotFoundException() {
        super();
    }

    public PrivilegeNotFoundException(Long id) {
        super(String.format("Привилегия с id \"%d\" удалена или не была добавлена", id));
    }

    public PrivilegeNotFoundException(String name) {
        super(String.format("Привилегия с наименованием \"%s\" удалена или не была добавлена", name));
    }

}
