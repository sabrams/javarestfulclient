package com.restfulclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.restfulclient.entity.Entity;
import com.restfulclient.entity.HttpEntity;
import com.restfulclient.exception.EntityAttributeMissingException;
import com.restfulclient.exception.ResourceDeserializationException;
import com.restfulclient.exception.ResourceSerializationException;
import com.restfulclient.serialization.EntitySerializer;
import com.restfulclient.serialization.JsonSerializer;

public class InternetMediaTypeEntitySerializerFactory implements
        EntitySerializerFactory {

    static final Map<String, EntitySerializer> serializerMap = new HashMap<String, EntitySerializer>();

    static {
        serializerMap.put("application/json", new JsonSerializer());
    }

    @Override
    public <T extends RestfulAwareResource> T deserialize(Entity entity,
            Class<T> clazz) throws ResourceDeserializationException {
        try {
            String contentType = entity.getContentType();
            InputStream content = entity.getContent();
            EntitySerializer serializer = serializerMap.get(contentType);
            return serializer.deserialize(content, clazz);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (EntityAttributeMissingException e) {
            throw new ResourceDeserializationException(e);
        }
        return null;
    }

    @Override
    public void serialize(RestfulAwareResource resource, OutputStream os) {
        EntitySerializer serializer = serializerMap.get(resource
                .getContentType());
        try {
            serializer.serialize(resource, os);
        } catch (ResourceSerializationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // @Override
    // public Entity serialize(RestfulAwareResource resource) {
    // Entity entity = new HttpEntity();
    // }

}
