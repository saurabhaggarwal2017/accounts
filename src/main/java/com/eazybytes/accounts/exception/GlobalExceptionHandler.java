package com.eazybytes.accounts.exception;

import com.eazybytes.accounts.dto.ErrorResponseDto;
import com.eazybytes.accounts.dto.ValidationErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistException(CustomerAlreadyExistsException exception, HttpServletRequest request) {
        String apiPath = String.format("%s %s", request.getMethod(), request.getRequestURI());
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                apiPath,
                HttpStatus.BAD_REQUEST.toString(),
                exception.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception, HttpServletRequest request) {
        String apiPath = String.format("%s %s", request.getMethod(), request.getRequestURI());
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                apiPath,
                HttpStatus.NOT_FOUND.toString(),
                exception.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponseDto> handleDataAccessException(DataAccessException exception, HttpServletRequest request) {
        String apiPath = String.format("%s %s", request.getMethod(), request.getRequestURI());
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                apiPath,
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                "Database operation failed there might be some issue please try again later.",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDto);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleConstraintViolationException(ConstraintViolationException exception, HttpServletRequest request) {
        String apiPath = String.format("%s %s", request.getMethod(), request.getRequestURI());
        String[] splitArray = exception.getMessage().split(":");
        String errorMessage = splitArray.length > 1 ? splitArray[1] : "path variable not valid.";

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                apiPath,
                HttpStatus.BAD_REQUEST.toString(),
                errorMessage.trim(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleDataAccessException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        String apiPath = String.format("%s %s", request.getMethod(), request.getRequestURI());
        Map<String, String> allErrors = new HashMap<>();
        for (ObjectError error : exception.getBindingResult().getAllErrors()) {
            allErrors.put(((FieldError) error).getField(), error.getDefaultMessage());
        }
        ValidationErrorResponse errorResponse = new ValidationErrorResponse(
                apiPath,
                allErrors.size(),
                HttpStatus.BAD_REQUEST.toString(),
                "There are some invalid input validation please check them below",
                allErrors,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    //@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception exception, HttpServletRequest request) {
        String apiPath = String.format("%s %s", request.getMethod(), request.getRequestURI());
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                apiPath,
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                exception.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDto);
    }
}
