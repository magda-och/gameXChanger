package com.gt.gamexchanger.exception;

public class NoRequestExistException extends RuntimeException {

    public NoRequestExistException() {super("Request dosen't exist");
    }
}