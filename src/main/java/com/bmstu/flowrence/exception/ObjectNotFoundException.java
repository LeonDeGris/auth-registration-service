package com.bmstu.flowrence.exception;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String uuid) {
        super(String.format("Object with identifier %s not found",
                uuid));
    }
}
