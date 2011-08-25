package com.restfulclient;

import java.io.InputStream;

import org.apache.http.Header;

import com.restfulclient.serialization.EntitySerializer;

public interface EntitySerializerFactory {

//    EntitySerializer getInstance(Entity entity);

    <T extends RestfulAwareResource> T deserialize(Entity entity, Class<T> restfulAwareResourceClass);

    Entity serialize(RestfulAwareResource resource);

}
