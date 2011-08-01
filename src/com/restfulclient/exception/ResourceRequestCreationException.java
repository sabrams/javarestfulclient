package com.restfulclient.exception;

public class ResourceRequestCreationException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 23959667585285731L;

    public ResourceRequestCreationException(String message) {
	super(message);
    }

    public ResourceRequestCreationException(String message, Exception cause) {
	super(message, cause);
    }
}
