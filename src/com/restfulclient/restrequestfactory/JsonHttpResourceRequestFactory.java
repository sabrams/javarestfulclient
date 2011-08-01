package com.restfulclient.restrequestfactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.clogs.externalservice.restfulclient.RestfulAwareResource;
import com.clogs.externalservice.restfulclient.exception.ResourceDeserializationException;


public class JsonHttpResourceRequestFactory<T extends RestfulAwareResource> extends HttpResourceRequestFactory<T> {

    Class clazz;
    
    public JsonHttpResourceRequestFactory(String host, String port) {
	super(host, port);
	clazz = this.
    }

    public JsonHttpResourceRequestFactory(String host, String port, ObjectMapper mapper) {
	super(host, port);
	this.mapper = mapper;
    }

    private ObjectMapper mapper;

    // public JsonHttpResourceRequestHelper(ObjectMapper mapper) {
    // this.mapper = mapper;
    //
    // }

    @SuppressWarnings("unchecked")
    @Override
    public T deserializeResource(InputStream is) throws ResourceDeserializationException {
	T result = null;
	try {
	    result = (T) mapper.readValue(is, resource.);
	} catch (JsonParseException e) {
	    throw new ResourceDeserializationException(e);
	} catch (JsonMappingException e) {
	    throw new ResourceDeserializationException(e);
	} catch (IOException e) {
	    throw new ResourceDeserializationException(e);
	}
	return result;
    }

    @Override
    public Collection<T> deserializeResourceCollection(InputStream is) throws ResourceDeserializationException {
	List<T> result = null;
	try {
	    result = mapper.readValue(is, new TypeReference<List<T>>() { // this is why I need a factory for each type... workaround?
	    });
	} catch (JsonParseException e) {
	    throw new ResourceDeserializationException(e);
	} catch (JsonMappingException e) {
	    throw new ResourceDeserializationException(e);
	} catch (IOException e) {
	    throw new ResourceDeserializationException(e);
	}
	return result;
    }


}