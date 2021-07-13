package com.example.versionDriver.exceptions;

import com.example.versionDriver.models.ExceptionBodyModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionsHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ExceptionBodyModel> handleException(GenericException ex ) {
        ExceptionBodyModel exceptionBody  = new ExceptionBodyModel(HttpStatus.BAD_REQUEST , ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionBody);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ExceptionBodyModel> handleIOException(IOException ex) {
        ExceptionBodyModel exceptionBody = new ExceptionBodyModel(HttpStatus.INTERNAL_SERVER_ERROR , ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionBody);
    }


}
