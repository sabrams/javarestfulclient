package com.restfulclient.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.Header;

import com.restfulclient.exception.EntityAttributeMissingException;

public class HttpEntity implements Entity {

    private String contentType;

    private static final String CONTENT_TYPE = "Content Type";

    private InputStream content;

    private org.apache.http.HttpEntity entity;

    public HttpEntity(org.apache.http.HttpEntity entity) {
        this.entity = entity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.restfulclient.Entity#getContentType()
     */
    @Override
    public String getContentType() throws EntityAttributeMissingException {
        if (entity.getContentType() == null)
            throw new EntityAttributeMissingException(CONTENT_TYPE);
        return entity.getContentType().getValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.restfulclient.Entity#getContent()
     */
    @Override
    public InputStream getContent() throws IllegalStateException, IOException {
        return entity.getContent();
    }

    @Override
    public long getContentLength() {
        // TODO Auto-generated method stub
        return 0;
    }

    // public org.apache.http.HttpEntity generateApacheEntity() {
    // return new HttpEntity() {
    //
    // @Override
    // public void writeTo(OutputStream outstream) throws IOException {
    // // TODO Auto-generated method stub
    //
    // }
    //
    // @Override
    // public boolean isStreaming() {
    // // TODO Auto-generated method stub
    // return false;
    // }
    //
    // @Override
    // public boolean isRepeatable() {
    // // TODO Auto-generated method stub
    // return false;
    // }
    //
    // @Override
    // public boolean isChunked() {
    // // TODO Auto-generated method stub
    // return false;
    // }
    //
    // @Override
    // public Header getContentType() {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //
    // @Override
    // public long getContentLength() {
    // // TODO Auto-generated method stub
    // return 0;
    // }
    //
    // @Override
    // public Header getContentEncoding() {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //
    // @Override
    // public InputStream getContent() throws IOException, IllegalStateException
    // {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //
    // @Override
    // public void consumeContent() throws IOException {
    // // TODO Auto-generated method stub
    //
    // }
    // };
    // }

}
