package com.restfulclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Entity {

    public String getContentType();

    public InputStream getContent() throws IllegalStateException, IOException;
    
//    public void writeTo(OutputStream os);

    public long getContentLength();
    

}