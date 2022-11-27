package com.samplebank.accountservice.controller.advice;

import com.samplebank.accountservice.dto.ErrorDTO;
import com.samplebank.accountservice.exception.CustomerNotFoundException;
import com.samplebank.accountservice.exception.TransactionManagementClientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDTO> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getPropertyPath() + violation.getMessage());
        }

        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST,"Constraint Violation Error", errors);
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionManagementClientException.class)
    public ResponseEntity<ErrorDTO> handleTransactionManagementClientException(TransactionManagementClientException ex) {
        HttpStatus httpStatus = HttpStatus.valueOf(ex.getStatus());

        ErrorDTO errorDTO = new ErrorDTO(httpStatus,"Client Error Error", ex.getMessage());
        return new ResponseEntity<>(errorDTO, httpStatus);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.NOT_FOUND,"Customer Not Found Error", ex.getMessage());
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }
}
