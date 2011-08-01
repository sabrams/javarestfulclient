package com.restfulclient;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.restfulclient.exception.ResourceDeserializationException;
import com.restfulclient.exception.ResourceNotRegisteredException;
import com.restfulclient.exception.ResourceRequestCreationException;
import com.restfulclient.exception.ResourceUriUnknownException;
import com.restfulclient.restrequestfactory.HttpResourceRequestFactory;

public class RestfulHttpClient {

    HttpClient httpclient = new DefaultHttpClient();

    Map<Class<? extends RestfulAwareResource>, HttpResourceRequestFactory<? extends RestfulAwareResource>> restfulResourceMap = new HashMap<Class<? extends RestfulAwareResource>, HttpResourceRequestFactory<? extends RestfulAwareResource>>();

    Class<? extends HttpResourceRequestFactory<?>> factoryClass;

    private String host;
    private String port;

    public RestfulHttpClient(String host, String port) {
	this.host = host;
	this.port = port;
    }

    public void setHttpClient(HttpClient httpClient) {
	this.httpclient = httpClient;
    }

    // public abstract void registerRestfulAwareResources();

//    public void registerResource(Class<? extends RestfulAwareResource> clazz, HttpResourceRequestFactory<? extends RestfulAwareResource> factory) {
//	factory.setHost(this.host);
//	factory.setPort(this.port);
//	restfulResourceMap.put(clazz, factory);
//    }

    public <T extends RestfulAwareResource> RestfulAwareResource get(RestfulAwareResource resource) throws ResourceUriUnknownException,
	    ResourceRequestCreationException, ResourceDeserializationException, ResourceNotRegisteredException {
	if (resource.getUri() == null)
	    throw new ResourceUriUnknownException();

	HttpResourceRequestFactory<? extends RestfulAwareResource> factory = restfulResourceMap.get(resource.getClass());
	if (factory == null) {
	    throw new ResourceNotRegisteredException(resource.getClass() + " has not been registered through 'registerResource'");
	}
	HttpGet getRequest = factory.createReadResourceRequest(resource);
	InputStream is = execute(getRequest);
//	return factory.deserializeResource(is, resource.getClass());

    }

    private InputStream execute(HttpGet getRequest) {
	try {
	    HttpResponse response = httpclient.execute(getRequest);
	    HttpEntity entity = response.getEntity();
	    if (entity != null) {
		return entity.getContent();
	    }

	} catch (ClientProtocolException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;

    }

}
