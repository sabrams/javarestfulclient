package com.restfulclient.serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.restfulclient.Entity;
import com.restfulclient.RestfulAwareResource;
import com.restfulclient.exception.ResourceDeserializationException;
import com.restfulclient.exception.ResourceSerializationException;

public class JsonSerializer implements EntitySerializer {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T extends RestfulAwareResource> void serialize(T resource,
            OutputStream os) throws ResourceSerializationException {

        try {
            mapper.writeValue(os, resource);
        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public <T extends RestfulAwareResource> T deserialize(InputStream is,
            Class<T> clazz) {

        try {
            return mapper.readValue(is, clazz);
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
