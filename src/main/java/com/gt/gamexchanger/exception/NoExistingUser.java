package com.gt.gamexchanger.exception;

public class NoExistingUser extends RuntimeException{
    public NoExistingUser(){
        super("User doesn't exist");
    }
}
