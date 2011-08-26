package com.restfulclient;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.Header;

import com.restfulclient.entity.Entity;
import com.restfulclient.exception.ResourceDeserializationException;
import com.restfulclient.serialization.EntitySerializer;

public interface EntitySerializerFactory {

//    EntitySerializer getInstance(Entity entity);

    <T extends RestfulAwareResource> T deserialize(Entity entity, Class<T> restfulAwareResourceClass) throws ResourceDeserializationException;

    void serialize(RestfulAwareResource resource, OutputStream os);

}
