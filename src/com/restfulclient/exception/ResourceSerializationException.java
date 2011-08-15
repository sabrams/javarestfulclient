package com.restfulclient.exception;

public class ResourceSerializationException extends Exception {

    private static final long serialVersionUID = -6894666497388603591L;

    public ResourceSerializationException(String message) {
        super(message);
    }

    public ResourceSerializationException(String message, Exception cause) {
        super(message, cause);
    }

    public ResourceSerializationException(Exception cause) {
        super(cause);
    }
}
