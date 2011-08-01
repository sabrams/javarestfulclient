package com.restfulclient.restrequestfactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Collection;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.restfulclient.exception.ResourceDeserializationException;
import com.restfulclient.exception.ResourceRequestCreationException;

public interface RestfulResourceRequestFactory<T> {    

    /**
     * 
     * @return READ request for a collection of a resource type
     */
    HttpGet createReadResourceCollectionRequest(T resource);

    /**
     * 
     * @return READ request for a particular resource
     * @throws Exception 
     */
    HttpGet createReadResourceRequest(T resource) throws ResourceRequestCreationException;

    /**
     * 
     * @return CREATE request to replace a collection
     * @throws ResourceRequestCreationException 
     */
    HttpPut createCreateResourceCollectionRequest(T resource) throws ResourceRequestCreationException;

    /**
     * 
     * @return CREATE request to update a resource
     */
    HttpPut createCreateResourceRequest(T resource) throws ResourceRequestCreationException;

    /**
     * 
     * @return UPDATE request to create a new collection
     */
    HttpPost createUpdateResourceCollectionRequest(T resource) throws ResourceRequestCreationException ;

    /**
     * 
     * @return UPDATE request to create a new resource
     */
    HttpPost createUpdateResourceRequest(T resource) throws ResourceRequestCreationException ;

    /**
     * 
     * @return DELETE request to delete a collection
     */
    HttpDelete createDeleteResourceCollectionRequest(T resource) throws ResourceRequestCreationException ;

    /**
     * 
     * @return DELETE request to delete a resource
     */
    HttpDelete createDeleteResourceRequest(T resource) throws ResourceRequestCreationException ;

    /**
     * 
     * @param is
     *            input stream containing serialized representation of
     *            collection of objects of the defined type
     * @return a collection of objects of the defined type
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    Collection<T> deserializeResourceCollection(InputStream is) throws ResourceDeserializationException;

    /**
     * 
     * @param is
     *            input stream containing serialized representation of an object
     *            of the defined type
     * @return the object, deserialized
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    T deserializeResource(InputStream is) throws ResourceDeserializationException;
}
