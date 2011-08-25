package com.restfulclient;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;

import com.restfulclient.exception.ResourceDeserializationException;
import com.restfulclient.exception.ResourceNotRegisteredException;
import com.restfulclient.exception.ResourceRequestCreationException;
import com.restfulclient.exception.ResourceUriInvalidException;

public abstract class AbstractRestfulClient implements
        RestfulClient<RestfulAwareResource> {

//
//    private final EntitySerializerFactory entitySerializerFactory;
//    
//    public AbstractRestfulClient(EntitySerializerFactory entitySerializerFactory) {
//        this.entitySerializerFactory = entitySerializerFactory;
//    }
// 
//    
//    @Override
//    public RestfulAwareResource get(RestfulAwareResource resource)
//            throws ResourceUriInvalidException,
//            ResourceRequestCreationException, ResourceDeserializationException,
//            ResourceNotRegisteredException, IOException {
//        
//        Entity result = doGet(resource);
//        
//        return entitySerializerFactory.getInstance(result.getContentType()).deserialize(result.getContent());
//
//    }
//    
//    
//
//    @Override
//    public RestfulAwareResource get(Class<? extends RestfulAwareResource> clazz)
//            throws ResourceUriInvalidException,
//            ResourceRequestCreationException, ResourceDeserializationException,
//            ResourceNotRegisteredException, IOException {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//
//    @Override
//    public void put(RestfulAwareResource resource)
//            throws ResourceUriInvalidException,
//            ResourceRequestCreationException, ResourceDeserializationException,
//            ResourceNotRegisteredException, IOException {
//        // TODO Auto-generated method stub
//        
//    }
//
//
//    @Override
//    public void delete(RestfulAwareResource resource)
//            throws ResourceUriInvalidException,
//            ResourceRequestCreationException, ResourceDeserializationException,
//            ResourceNotRegisteredException, IOException {
//        // TODO Auto-generated method stub
//        
//    }
//
//
//
//    protected abstract Entity doGet(RestfulAwareResource resource);
//
//    protected abstract Entity doPut(RestfulAwareResource resource); 


}