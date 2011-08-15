package com.restfulclient.serialization;

import java.io.InputStream;

import com.restfulclient.RestfulAwareResource;
import com.restfulclient.exception.ResourceDeserializationException;
import com.restfulclient.exception.ResourceSerializationException;

public interface EntitySerializer {
    
    public Object serialize(RestfulAwareResource resource) throws ResourceSerializationException;
    
    public RestfulAwareResource deserialize(InputStream o) throws ResourceDeserializationException;

}
