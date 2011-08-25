package com.restfulclient;

import java.io.IOException;

import com.restfulclient.exception.ResourceDeserializationException;
import com.restfulclient.exception.ResourceNotRegisteredException;
import com.restfulclient.exception.ResourceRequestCreationException;
import com.restfulclient.exception.ResourceUriInvalidException;

/**
 * 
 * @author stephenabrams
 * 
 *         Strict adherence to this interface will enforce RESTful methods/verbs:
 *         get/post/delete/put. Any failures to execute these methods
 *         should throw an appropriate exception
 * 
 * @param <T extends {@link RestfulAwareResource}>
 * 
 */
public interface RestfulClient<T extends RestfulAwareResource> {

    public T get(Class<? extends RestfulAwareResource> clazz) throws ResourceUriInvalidException, ResourceRequestCreationException,
            ResourceDeserializationException, ResourceNotRegisteredException, IOException;

    public T get(RestfulAwareResource resource) throws ResourceUriInvalidException, ResourceRequestCreationException,
            ResourceDeserializationException, ResourceNotRegisteredException, IOException;

    public void put(RestfulAwareResource resource) throws ResourceUriInvalidException, ResourceRequestCreationException,
            ResourceDeserializationException, ResourceNotRegisteredException, IOException;

    public void delete(RestfulAwareResource resource) throws ResourceUriInvalidException, ResourceRequestCreationException,
            ResourceDeserializationException, ResourceNotRegisteredException, IOException;

    public void post(RestfulAwareResource resource) throws ResourceUriInvalidException, ResourceRequestCreationException,
            ResourceDeserializationException, ResourceNotRegisteredException, IOException;

}