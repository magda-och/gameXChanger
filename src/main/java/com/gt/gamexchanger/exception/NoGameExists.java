package com.gt.gamexchanger.exception;

public class NoGameExists extends RuntimeException {

    public NoGameExists() {
        super("No Game exist");
    }
}
