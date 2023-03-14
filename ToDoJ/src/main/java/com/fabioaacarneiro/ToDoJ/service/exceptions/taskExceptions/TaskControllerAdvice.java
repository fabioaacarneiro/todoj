package com.fabioaacarneiro.ToDoJ.service.exceptions.taskExceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fabioaacarneiro.ToDoJ.service.exceptions.FieldAndErrorHandler;
import com.fabioaacarneiro.ToDoJ.service.exceptions.MessageExceptionHandler;

/**
 * TaskControllerAdvice
 */
@ControllerAdvice(basePackages = "com.fabioaacarneiro.ToDoJ.controller")
public class TaskControllerAdvice {

    @ResponseBody
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> taskNotFound(TaskNotFoundException taskNotFound) {

        FieldAndErrorHandler fieldAndError = new FieldAndErrorHandler("error", "Task is not found.");
        List<FieldAndErrorHandler> fieldsAndErrors = new ArrayList<>();

        fieldsAndErrors.add(fieldAndError);

        MessageExceptionHandler error = MessageExceptionHandler.builder()
            .timestamp(new Date())
            .status(HttpStatus.NOT_FOUND.value())
            .message("The task informed by id was not found in the database.")
            .errors(fieldsAndErrors)
            .build();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(TaskListIsEmptyException.class)
    public ResponseEntity<MessageExceptionHandler> taskListIsEmpty(TaskListIsEmptyException taskListIsEmpty) {

        FieldAndErrorHandler fieldAndError = new FieldAndErrorHandler("error", "The task list is empty.");
        List<FieldAndErrorHandler> fieldsAndErrors = new ArrayList<>();

        fieldsAndErrors.add(fieldAndError);

        MessageExceptionHandler error = MessageExceptionHandler.builder()
            .timestamp(new Date())
            .status(HttpStatus.NOT_FOUND.value())
            .message("There aren't tasks for today, create new or go back later.")
            .errors(fieldsAndErrors)
            .build();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
    @ResponseBody
    @ExceptionHandler(TaskNotDeleteException.class)
    public ResponseEntity<MessageExceptionHandler> taskCannotDelete(TaskListIsEmptyException taskListIsEmpty) {

        FieldAndErrorHandler fieldAndError = new FieldAndErrorHandler("error", "The task is not found.");
        List<FieldAndErrorHandler> fieldsAndErrors = new ArrayList<>();

        fieldsAndErrors.add(fieldAndError);

        MessageExceptionHandler error = MessageExceptionHandler.builder()
            .timestamp(new Date())
            .status(HttpStatus.NOT_FOUND.value())
            .message("The task informed by id was not found in the database.")
            .errors(fieldsAndErrors)
            .build();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageExceptionHandler> taskNotInserted(MethodArgumentNotValidException notValid) {

        List<FieldAndErrorHandler> fieldsAndErrors = new ArrayList<>();

        BindingResult result = notValid.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            FieldAndErrorHandler fieldAndError = FieldAndErrorHandler.builder()
                .field(fieldError.getField())
                .error("The field is empty.")
                .build();

            fieldsAndErrors.add(fieldAndError);
        }

        MessageExceptionHandler error = MessageExceptionHandler.builder()
            .timestamp(new Date())
            .status(HttpStatus.BAD_REQUEST.value())
            .message("One or more fields are null or invalid.")
            .errors(fieldsAndErrors)
            .build();
            
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
