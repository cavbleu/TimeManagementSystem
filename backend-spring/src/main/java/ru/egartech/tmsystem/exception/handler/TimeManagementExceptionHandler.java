package ru.egartech.tmsystem.exception.handler;

import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.egartech.tmsystem.exception.*;
import ru.egartech.tmsystem.exception.dto.ApiError;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class TimeManagementExceptionHandler extends ResponseEntityExceptionHandler {


    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<Object> handleException(EntityNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        apiError.setStackTrace(ExceptionUtils.getStackTrace(ex));
        ExceptionUtils.getStackTrace(ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ActiveProfileDeleteException.class)
    ResponseEntity<Object> handleException(ActiveProfileDeleteException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        apiError.setStackTrace(ExceptionUtils.getStackTrace(ex));
        ExceptionUtils.getStackTrace(ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ActiveProfileNotInstalledException.class)
    ResponseEntity<Object> handleException(ActiveProfileNotInstalledException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        apiError.setStackTrace(ExceptionUtils.getStackTrace(ex));
        ExceptionUtils.getStackTrace(ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(SettingsOneProfileException.class)
    ResponseEntity<Object> handleException(SettingsOneProfileException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        apiError.setStackTrace(ExceptionUtils.getStackTrace(ex));
        ExceptionUtils.getStackTrace(ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(DurationException.class)
    ResponseEntity<Object> handleException(DurationException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        apiError.setStackTrace(ExceptionUtils.getStackTrace(ex));
        ExceptionUtils.getStackTrace(ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(StartDateEarlierException.class)
    ResponseEntity<Object> handleException(StartDateEarlierException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        apiError.setStackTrace(ExceptionUtils.getStackTrace(ex));
        ExceptionUtils.getStackTrace(ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(DepartmentConstraintException.class)
    ResponseEntity<Object> handleException(DepartmentConstraintException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        apiError.setStackTrace(ExceptionUtils.getStackTrace(ex));
        ExceptionUtils.getStackTrace(ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(PositionConstraintException.class)
    ResponseEntity<Object> handleException(PositionConstraintException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        apiError.setStackTrace(ExceptionUtils.getStackTrace(ex));
        ExceptionUtils.getStackTrace(ex);
        return buildResponseEntity(apiError);
    }
}
