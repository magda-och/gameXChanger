package com.gt.gamexchanger.exception;

public class NoDataFoundException extends RuntimeException {

    public NoDataFoundException() {
        super("You didn't filled all fields correctly");
    }
}
