package ru.egartech.tmsystem.exception;

public class StartDateEarlierException extends RuntimeException{

    public StartDateEarlierException() {
        super("Дата начала отчетного периода не может быть позднее даты окончания отчетного периода");
    }
}
