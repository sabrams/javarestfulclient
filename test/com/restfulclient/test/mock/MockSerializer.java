package com.restfulclient.test.mock;

import java.io.IOException;
import java.io.InputStream;

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

    @Override
    public Object serialize(RestfulAwareResource resource)
            throws ResourceSerializationException {

        return stubResource;
    }

    @Override
    public RestfulAwareResource deserialize(InputStream o)
            throws ResourceDeserializationException {
        try {
            lastByteArray = new byte[o.available()];
            o.read(lastByteArray);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return stubResource;
    }

    public byte[] getLastByteArray() {
        return lastByteArray;
    }

}
