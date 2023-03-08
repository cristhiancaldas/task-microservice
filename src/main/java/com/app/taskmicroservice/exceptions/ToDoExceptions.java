package com.app.taskmicroservice.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ToDoExceptions extends RuntimeException{

    private final String message;

    private final HttpStatus httpStatus;

    public ToDoExceptions(String message,HttpStatus httpStatus){
        this.message=message;
        this.httpStatus= httpStatus;
    }
}
