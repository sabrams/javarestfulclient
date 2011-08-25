package com.restfulclient.test.mock;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.restfulclient.RestfulAwareResource;
import com.restfulclient.exception.ResourceDeserializationException;
import com.restfulclient.exception.ResourceSerializationException;
import com.restfulclient.serialization.EntitySerializer;

public class MockSerializer implements EntitySerializer {

    RestfulAwareResource stubResource;

    byte[] lastByteArray;

    public void setStubResource(RestfulAwareResource stubResource) {
        this.stubResource = stubResource;
    }

    public byte[] getLastByteArray() {
        return lastByteArray;
    }

    @Override
    public <T extends RestfulAwareResource> void serialize(T resource,
            OutputStream os) throws ResourceSerializationException {
        // todo: something with os
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends RestfulAwareResource> T deserialize(InputStream is,
            Class<T> clazz) {
        try {
            lastByteArray = new byte[is.available()];
            is.read(lastByteArray);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return (T) stubResource;
    }

}
