package com.leodesanmiguel.fichadocente.exceptions;

import com.leodesanmiguel.fichadocente.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandler {
    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> methodArgumentNotValidException(HttpServletRequest request,MethodArgumentNotValidException e) {

        // Los errores de las validaciones de los DTO
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        // convert errors to standard string
        StringBuilder errorMessage = new StringBuilder();
        fieldErrors.forEach(f -> errorMessage.append(f.getField() + " " + f.getDefaultMessage() +  " "));

        // return error info object with standard json
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST, errorMessage.toString(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }*/

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> notFoundException(UserNotFoundException e){
        ErrorDto errorDto = new ErrorDto(404, e.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validacionErronea(MethodArgumentNotValidException ex){

        //ErrorDto errorDto = new ErrorDto(400,ex.getFieldError().getDefaultMessage());

        List<ErrorDto> errores = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> new ErrorDto(400,err.getDefaultMessage()))
                .distinct()
                .collect(Collectors.toList());

        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }
}
