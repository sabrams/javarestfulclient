package com.restfulclient;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.restfulclient.serialization.EntitySerializer;
import com.restfulclient.serialization.JsonSerializer;

public class InternetMediaTypeEntitySerializerFactory implements
        EntitySerializerFactory {

    static final Map<String, EntitySerializer> serializerMap = new HashMap<String, EntitySerializer>();

    static {
        serializerMap.put("application/json", new JsonSerializer());
    }

//    @Override
//    public EntitySerializer getInstance(Entity entity) {
//        // TODO Auto-generated method stub
//        return null;
//    }

    @Override
    public <T extends RestfulAwareResource> T deserialize(Entity entity,
            Class<T> clazz) {
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
        }
        return null;
    }

    @Override
    public Entity serialize(RestfulAwareResource resource) {
        // TODO Auto-generated method stub
        return null;
    }

}
