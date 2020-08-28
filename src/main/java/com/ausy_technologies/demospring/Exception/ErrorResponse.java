package com.ausy_technologies.demospring.Exception;

public class ErrorResponse extends RuntimeException{
    public ErrorResponse(String message){
        super(message);
    }
}
