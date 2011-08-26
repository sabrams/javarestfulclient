package com.restfulclient.exception;

public class EntityAttributeMissingException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final String MESSAGE_PREFIX = "Entity Attribute Missing: "; 

    public EntityAttributeMissingException(String missingAttribute) {
        super(MESSAGE_PREFIX + missingAttribute);
        // TODO Auto-generated constructor stub
    }

//    public EntityAttributeMissingException(String message, Throwable cause) {
//        super(message, cause);
//        // TODO Auto-generated constructor stub
//    }
    
}
