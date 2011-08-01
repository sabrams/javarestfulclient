package com.restfulclient.exception;

public class ResourceDeserializationException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -2147944090889974470L;
    
    public ResourceDeserializationException(String message) {
	super(message);
    }

    public ResourceDeserializationException(String message, Exception cause) {
	super(message, cause);
    }
    
    public ResourceDeserializationException(Exception cause) {
	super(cause);
    }
}
