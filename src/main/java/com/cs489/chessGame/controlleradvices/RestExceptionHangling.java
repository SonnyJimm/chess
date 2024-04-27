package com.cs489.chessGame.controlleradvices;

import java.util.HashMap;
import java.util.Map;

import com.cs489.chessGame.error.CustomError;
import com.cs489.chessGame.error.CustomException;
import com.cs489.chessGame.error.Errors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.ConstraintViolationException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class RestExceptionHangling {
    @ExceptionHandler(value = { CustomException.class })
    public ResponseEntity<CustomError> handleCustomException(
            CustomException ex) {
        return new ResponseEntity<CustomError>(ex.getError(), ex.getError().getStatus());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String,String> data = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
        String fieldName;
            try {
                fieldName = ((FieldError) error).getField();

            } catch (ClassCastException ex) {
                fieldName = error.getObjectName();
            }
            String message = error.getDefaultMessage();
            data.put(fieldName, message);
        });
    CustomError customError = new CustomError(HttpStatus.BAD_REQUEST,"INVALID INPUT");
    customError.setDetails(data);
    return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
}
    @ExceptionHandler(value = { ConstraintViolationException.class })
    public ResponseEntity<CustomError> handleConstraintViolationException(ConstraintViolationException e) {
        try {
            Map<String, String> errors = new HashMap<>();
            e.getConstraintViolations()
                    .forEach(error -> errors.put(error.getInvalidValue().toString(), error.getMessage()));
            CustomError error = Errors.INVALID_VALUE;
            error.setDetails(errors);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<CustomError>(Errors.INVALID_VALUE, HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleFailedAuth(BadCredentialsException badCredentialsException) {
        var errorMsgMap = new HashMap<String, String>();
        errorMsgMap.put("errorMsg", badCredentialsException.getMessage());
        return errorMsgMap;
    }
    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HashMap<String, String> handleNoHandlerFound(NoResourceFoundException e, WebRequest request) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", e.getLocalizedMessage());
        return response;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HashMap<String, String> handleNoMethodSupported(HttpRequestMethodNotSupportedException e) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", e.getLocalizedMessage());
        return response;
    }
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<CustomError> handleInternalError(
            Exception ex) {
        System.out.println(ex);
        return new ResponseEntity<>(Errors.INTERNAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ AuthenticationException.class })
    @ResponseBody
    public ResponseEntity<CustomError> handleAuthenticationException(Exception ex) {
        System.out.println(ex);
        CustomError re = new CustomError(HttpStatus.UNAUTHORIZED,
                "Wrong credential");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(re);
    }
}
