package com.bmstu.flowrence.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String uuid) {
        super(String.format("No user found with id %s", uuid));
    }
}
