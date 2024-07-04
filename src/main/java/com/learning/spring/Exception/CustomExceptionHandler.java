package com.learning.spring.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.util.Date;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage resourceNotFound(ResourceNotFoundException ex, WebRequest request){
        ErrorMessage message = new ErrorMessage(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(),new Date(),request.getDescription(false));
        log.error(String.valueOf(message));
        return message;
    }
    @ExceptionHandler(ResourceFoundException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage resourceNotFound(ResourceFoundException ex, WebRequest request){
        ErrorMessage message = new ErrorMessage(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(),new Date(),request.getDescription(false));
        log.error(String.valueOf(message));
        return message;
    }
    @ExceptionHandler(GeneralException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage generalException(GeneralException ex, WebRequest request){
        ErrorMessage message = new ErrorMessage(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(),new Date(),request.getDescription(false));
        log.error(String.valueOf(message));
        return message;
    }
    @ExceptionHandler(AuthenticationFailedException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage authenticationFailedException(AuthenticationFailedException ex, WebRequest request){
        ErrorMessage message = new ErrorMessage(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(),new Date(),request.getDescription(false));
        log.error(String.valueOf(message));
        return message;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage globalException(Exception ex, WebRequest request){
        ErrorMessage message = new ErrorMessage(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),new Date(),request.getDescription(false));
        log.error(String.valueOf(message));
        return message;
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage globalException(IOException ex, WebRequest request){
        ErrorMessage message = new ErrorMessage(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),new Date(),request.getDescription(false));
        log.error(String.valueOf(message));
        return message;
    }


}
