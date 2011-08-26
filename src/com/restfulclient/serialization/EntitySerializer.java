package com.restfulclient.serialization;

import java.io.InputStream;
import java.io.OutputStream;

import com.restfulclient.RestfulAwareResource;
import com.restfulclient.entity.Entity;
import com.restfulclient.exception.ResourceDeserializationException;
import com.restfulclient.exception.ResourceSerializationException;

public interface EntitySerializer {

    public <T extends RestfulAwareResource> void serialize(T resource,
            OutputStream os) throws ResourceSerializationException;

    public <T extends RestfulAwareResource> T deserialize(InputStream is,
            Class<T> clazz);

}
