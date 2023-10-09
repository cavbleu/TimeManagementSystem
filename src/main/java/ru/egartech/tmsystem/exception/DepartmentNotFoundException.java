package ru.egartech.tmsystem.exception;

public class DepartmentNotFoundException extends RuntimeException{

    public DepartmentNotFoundException() {
        super();
    }

    public DepartmentNotFoundException(Long id) {
        super(String.format("Отдел с id \"%d\" удален или не был добавлен", id));
    }
    public DepartmentNotFoundException(String name) {
        super(String.format("Отдел с наименованием \"%s\" удален или не был добавлен", name));
    }

}
