package com.restfulclient.restrequestfactory;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIUtils;

import com.restfulclient.RestfulAwareResource;
import com.restfulclient.exception.ResourceRequestCreationException;

public abstract class HttpResourceRequestFactory<T extends RestfulAwareResource> implements RestfulResourceRequestFactory<T> {

    static final String scheme = "http";
    String host;
    String port;

    public HttpResourceRequestFactory(String host, String port) {
	this.host = host;
	this.port = port;
    }
    
    public HttpResourceRequestFactory() {
    }


    @Override
    public HttpGet createReadResourceCollectionRequest(T resource) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public HttpGet createReadResourceRequest(RestfulAwareResource resource) throws ResourceRequestCreationException {

	URI uri;
	try {
	    uri = URIUtils.createURI(scheme, host, 3000, "/events.json", null, null);
	} catch (URISyntaxException e) {
	    throw new ResourceRequestCreationException("URL Syntax problem", e);
	}
	return new HttpGet(uri);

    }

    @Override
    public HttpPut createCreateResourceCollectionRequest(RestfulAwareResource resource) throws ResourceRequestCreationException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public HttpPut createCreateResourceRequest(RestfulAwareResource resource) throws ResourceRequestCreationException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public HttpPost createUpdateResourceCollectionRequest(RestfulAwareResource resource) throws ResourceRequestCreationException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public HttpPost createUpdateResourceRequest(RestfulAwareResource resource) throws ResourceRequestCreationException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public HttpDelete createDeleteResourceCollectionRequest(RestfulAwareResource resource) throws ResourceRequestCreationException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public HttpDelete createDeleteResourceRequest(RestfulAwareResource resource) throws ResourceRequestCreationException {
	// TODO Auto-generated method stub
	return null;
    }

    public void setHost(String host) {
	this.host = host;

    }

    public void setPort(String port) {
	this.port = port;
    }

}