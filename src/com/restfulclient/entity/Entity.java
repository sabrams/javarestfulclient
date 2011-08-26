package com.restfulclient.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.restfulclient.exception.EntityAttributeMissingException;

public interface Entity {

    public String getContentType() throws EntityAttributeMissingException;

    public InputStream getContent() throws IllegalStateException, IOException;
    
//    public void writeTo(OutputStream os);

    public long getContentLength();
    

}