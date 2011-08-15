package com.restfulclient.test.mock.apachehttp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;

public class MockHttpEntity implements HttpEntity {

    InputStream inputStreamStub;

    @Override
    public void consumeContent() throws IOException {
        // TODO Auto-generated method stub

    }

    @Override
    public InputStream getContent() throws IOException, IllegalStateException {
        return inputStreamStub;
    }

    @Override
    public Header getContentEncoding() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getContentLength() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Header getContentType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isChunked() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isRepeatable() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isStreaming() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void writeTo(OutputStream arg0) throws IOException {
        // TODO Auto-generated method stub

    }

    public void setInputStreamStub(InputStream inputStreamStub) {
        this.inputStreamStub = inputStreamStub;
    }
}
