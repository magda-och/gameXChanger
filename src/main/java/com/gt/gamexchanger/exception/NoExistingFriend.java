package com.gt.gamexchanger.exception;

public class NoExistingFriend extends RuntimeException{
    public NoExistingFriend() {
        super("Friend doesn't exist");
    }
}
