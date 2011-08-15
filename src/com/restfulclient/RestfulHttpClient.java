package com.restfulclient;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;

import com.restfulclient.exception.ResourceDeserializationException;
import com.restfulclient.exception.ResourceNotRegisteredException;
import com.restfulclient.exception.ResourceRequestCreationException;
import com.restfulclient.exception.ResourceUriInvalidException;
import com.restfulclient.serialization.EntitySerializer;

public class RestfulHttpClient implements RestfulClient<RestfulAwareResource> {

    HttpClient httpClient = new DefaultHttpClient();

    EntitySerializer serializer;

    public void setSerializer(EntitySerializer serializer) {
        this.serializer = serializer;
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

        HttpResponse httpResponse = null;
        // TODO better validation in http context
        if (resource.getUri() == null)
            throw new ResourceUriInvalidException();

        HttpGet getMethod = new HttpGet(resource.getUri());
        try {
            httpResponse = httpClient.execute(getMethod);
        } catch (ClientProtocolException e) {
            // TODO When does this happen? is the invalid URI case covered here?
            // (hence omit top "throws")
            e.printStackTrace();
        }

        return serializer.deserialize(httpResponse.getEntity().getContent());

    }

    @Override
    public void put(RestfulAwareResource resource)
            throws ResourceUriInvalidException,
            ResourceRequestCreationException, ResourceDeserializationException,
            ResourceNotRegisteredException {
        HttpPut putMethod = new HttpPut(resource.getUri());
        // putMethod.getEntity().getContentEncoding().get

    }

    @Override
    public void delete(RestfulAwareResource resource)
            throws ResourceUriInvalidException,
            ResourceRequestCreationException, ResourceDeserializationException,
            ResourceNotRegisteredException {
        // TODO Auto-generated method stub

    }

    @Override
    public void post(RestfulAwareResource resource)
            throws ResourceUriInvalidException,
            ResourceRequestCreationException, ResourceDeserializationException,
            ResourceNotRegisteredException {
        // TODO Auto-generated method stub

    }

    private InputStream execute(HttpGet getRequest) {
        try {
            HttpResponse response = httpClient.execute(getRequest);
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
