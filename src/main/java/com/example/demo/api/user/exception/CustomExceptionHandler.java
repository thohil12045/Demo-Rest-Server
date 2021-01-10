package com.example.demo.api.user.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;

@ControllerAdvice(basePackages = {"com.example.demo.api"})
@Slf4j
public class CustomExceptionHandler
{
    @Value("${debug:true}")
    private boolean debug;

    @ExceptionHandler(value = {ConstraintViolationException.class,
            MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class})
    public final ResponseEntity<Object> handleValidationExceptions(Exception ex, WebRequest request) {
        return this.getErrorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return this.getErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
        return this.getErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> getErrorResponse(Throwable ex, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setCode(status.value());
        if (debug) {
            final Throwable cause = ex.getCause();
            final String errorClassName;
            if (cause != null) {
                errorClassName = cause.getClass().getSimpleName();
                errorResponse.setStacktrace(ex.getCause().getStackTrace());
            } else {
                errorClassName = ex.getClass().getSimpleName();
                errorResponse.setStacktrace(ex.getStackTrace());
            }
            errorResponse.setType(errorClassName);
        }
        return new ResponseEntity<>(errorResponse, status);
    }
}
