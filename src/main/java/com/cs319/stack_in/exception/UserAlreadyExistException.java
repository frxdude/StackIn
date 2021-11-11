package com.cs319.stack_in.exception;

public class UserAlreadyExistException extends RunTimeException{
    String message;
    public UserAlreadyExistException(String message){
            this.message = message;
    }
}
