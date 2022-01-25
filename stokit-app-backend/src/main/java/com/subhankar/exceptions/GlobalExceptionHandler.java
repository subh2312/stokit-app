package com.subhankar.exceptions;

import com.subhankar.entity.ExceptionDetail;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDetail> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request){
        ExceptionDetail exceptionDetail = new ExceptionDetail(new Date(),HttpStatus.NOT_FOUND+""+exception.getMessage(), request.getDescription(false), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(exceptionDetail,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<ExceptionDetail> handleInvalidCredentialException(InvalidCredentials exception, WebRequest request){
        ExceptionDetail exceptionDetail = new ExceptionDetail(new Date(),HttpStatus.UNAUTHORIZED+""+exception.getMessage(), request.getDescription(false), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(exceptionDetail,HttpStatus.UNAUTHORIZED);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName,message);
        });
        ExceptionDetail exceptionDetail= new ExceptionDetail(new Date(),errors.keySet().toString()+""+errors.values().toString(),request.getDescription(false),HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(exceptionDetail,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDetail> handleOtherExceptions(Exception exception, WebRequest request){
        ExceptionDetail exceptionDetail = new ExceptionDetail(new Date(),HttpStatus.INTERNAL_SERVER_ERROR+""+exception.getMessage(), request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(exceptionDetail,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

            ExceptionDetail exceptionDetail= new ExceptionDetail(new Date(),ex.getLocalizedMessage() +""+ex.getMessage(),request.getDescription(false),HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());

            return new ResponseEntity<>(exceptionDetail,HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
}
