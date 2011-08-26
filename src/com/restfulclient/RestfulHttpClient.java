package com.restfulclient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.restfulclient.entity.Entity;
import com.restfulclient.entity.HttpEntity;
import com.restfulclient.exception.ResourceDeserializationException;
import com.restfulclient.exception.ResourceNotRegisteredException;
import com.restfulclient.exception.ResourceRequestCreationException;
import com.restfulclient.exception.ResourceUriInvalidException;

/**
 * 
 * @author stephenabrams
 * 
 */
public class RestfulHttpClient implements RestfulClient<RestfulAwareResource> {

    HttpClient httpClient = new DefaultHttpClient();

    private final String type = "application/json"; // TODO: replace me with
                                                    // whereever this should
                                                    // come from

    private final EntitySerializerFactory entitySerializerFactory;

    public RestfulHttpClient() {
        this.entitySerializerFactory = new InternetMediaTypeEntitySerializerFactory();
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public RestfulAwareResource get(Class<? extends RestfulAwareResource> clazz)
            throws ResourceUriInvalidException,
            ResourceRequestCreationException, ResourceDeserializationException,
            ResourceNotRegisteredException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RestfulAwareResource get(RestfulAwareResource resource)
            throws ResourceUriInvalidException,
            ResourceRequestCreationException, ResourceDeserializationException,
            ResourceNotRegisteredException, IOException {

        validate(resource);

        HttpGet getMethod = new HttpGet(resource.getUri());

        HttpResponse httpResponse = executeMethod(getMethod);

        HttpEntity entity = new HttpEntity(httpResponse.getEntity());

        return entitySerializerFactory.deserialize(entity, resource.getClass());

    }

    @Override
    public void put(RestfulAwareResource resource)
            throws ResourceUriInvalidException,
            ResourceRequestCreationException, ResourceDeserializationException,
            ResourceNotRegisteredException {

        validate(resource);

        HttpPut putMethod = new HttpPut(resource.getUri());

        populateEntityEnclosingMethod(resource, putMethod);

        HttpResponse httpResponse = executeMethod(putMethod);

    }

    @Override
    public void delete(RestfulAwareResource resource)
            throws ResourceUriInvalidException,
            ResourceRequestCreationException, ResourceDeserializationException,
            ResourceNotRegisteredException {

        validate(resource);

        HttpDelete deleteMethod = new HttpDelete(resource.getUri());

        HttpResponse httpResponse = executeMethod(deleteMethod);

    }

    @Override
    public void post(RestfulAwareResource resource)
            throws ResourceUriInvalidException,
            ResourceRequestCreationException, ResourceDeserializationException,
            ResourceNotRegisteredException {
        HttpPost postMethod = new HttpPost(resource.getUri());

        populateEntityEnclosingMethod(resource, postMethod);

        HttpResponse httpResponse = executeMethod(postMethod);

    }

    private void validate(RestfulAwareResource resource)
            throws ResourceUriInvalidException {
        // TODO better validation in http context
        if (resource.getUri() == null)
            throw new ResourceUriInvalidException();
    }

    private void populateEntityEnclosingMethod(RestfulAwareResource resource,
            HttpEntityEnclosingRequestBase request) {
        // maybe the right one is there, or maybe entity should be protocol
        // agnostic
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            entitySerializerFactory.serialize(resource, os);

            org.apache.http.HttpEntity httpEntity = new ByteArrayEntity(
                    os.toByteArray());
            request.setEntity(httpEntity);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private HttpResponse executeMethod(HttpRequestBase method) {
        try {
            return httpClient.execute(method);
        } catch (ClientProtocolException e) {
            // TODO When does this happen? is the invalid URI case covered here?
            // (hence omit top "throws")
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    // private InputStream execute(HttpGet getRequest) {
    // try {
    // HttpResponse response = httpClient.execute(getRequest);
    // org.apache.http.HttpEntity entity = response.getEntity();
    // if (entity != null) {
    // return entity.getContent();
    // }
    //
    // } catch (ClientProtocolException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // } catch (IOException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // return null;
    //
    // }

}
