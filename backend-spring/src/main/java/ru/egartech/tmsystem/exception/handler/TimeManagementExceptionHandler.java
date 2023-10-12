package ru.egartech.tmsystem.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.egartech.tmsystem.exception.*;
import ru.egartech.tmsystem.exception.DistractionNotFoundException;

@ControllerAdvice
public class TimeManagementExceptionHandler {

    @ResponseBody
    @ExceptionHandler(DepartmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String timeManagementHandler(DepartmentNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(DistractionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String timeManagementHandler(DistractionNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String timeManagementHandler(EmployeeNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PositionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String timeManagementHandler(PositionNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PrivilegeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String timeManagementHandler(PrivilegeNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(RestNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String timeManagementHandler(RestNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SettingsNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String timeManagementHandler(SettingsNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TimeSheetNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String timeManagementHandler(TimeSheetNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CurrentSettingsNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String timeManagementHandler(CurrentSettingsNotFoundException ex) {
        return ex.getMessage();
    }
    @ResponseBody
    @ExceptionHandler(SettingsOneProfileException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String timeManagementHandler(SettingsOneProfileException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(DurationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String timeManagementHandler(DurationException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(StartDateEarlierException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String timeManagementHandler(StartDateEarlierException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(DepartmentConstraintException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String timeManagementHandler(DepartmentConstraintException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PositionConstraintException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String timeManagementHandler(PositionConstraintException ex) {
        return ex.getMessage();
    }
}
