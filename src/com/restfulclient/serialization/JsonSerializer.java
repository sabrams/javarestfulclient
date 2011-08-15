package com.restfulclient.serialization;

import java.io.InputStream;

import com.restfulclient.RestfulAwareResource;
import com.restfulclient.exception.ResourceDeserializationException;
import com.restfulclient.exception.ResourceSerializationException;

public class JsonSerializer implements EntitySerializer {


    @Override
    public Object serialize(RestfulAwareResource resource)
            throws ResourceSerializationException {
    
//        EntitySerializer httpEntitySerializer = new EntitySerializer(lenStrategy)
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RestfulAwareResource deserialize(InputStream is)
            throws ResourceDeserializationException {
        // TODO Auto-generated method stub
        return null;
    }

}
