package com.neosoft.spring_boot_poc.exception;

public class NoUserFoundException extends RuntimeException {

    public NoUserFoundException(String exception){
        super(exception);
    }
}
