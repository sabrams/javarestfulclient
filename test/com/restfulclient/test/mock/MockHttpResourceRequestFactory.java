package com.restfulclient.test.mock;

import java.io.InputStream;
import java.util.Collection;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import com.restfulclient.RestfulAwareResource;
import com.restfulclient.exception.ResourceDeserializationException;
import com.restfulclient.exception.ResourceRequestCreationException;
import com.restfulclient.restrequestfactory.HttpResourceRequestFactory;
import com.restfulclient.test.mock.apachehttp.MockHttpDelete;
import com.restfulclient.test.mock.apachehttp.MockHttpGet;
import com.restfulclient.test.mock.apachehttp.MockHttpPost;
import com.restfulclient.test.mock.apachehttp.MockHttpPut;

public class MockHttpResourceRequestFactory<T extends RestfulAwareResource> extends HttpResourceRequestFactory {

    public MockHttpResourceRequestFactory(String host, String port) {
	super(host, port);
    }

    public MockHttpResourceRequestFactory() {
	super();
    }

    HttpPut mockHttpPut = new MockHttpPut();
    HttpGet mockHttpGet = new MockHttpGet();
    HttpPost mockHttpPost = new MockHttpPost();
    HttpDelete mockHttpDelete = new MockHttpDelete();

    
    RestfulAwareResource deserializedResourceStub;

    private String resourceDeserializationExceptionMessageStub;      
    private String exceptionMessageStub;

    public RestfulAwareResource getDeserializedResourceStub() {
        return deserializedResourceStub;
    }

    public void setDeserializedResourceStub(RestfulAwareResource deserializedResourceStub) {
        this.deserializedResourceStub = deserializedResourceStub;
    }

    @Override
    public Collection<RestfulAwareResource> deserializeResourceCollection(InputStream is) throws ResourceDeserializationException {
	checkResourceDeserializationException();
	return null;
    }

    @Override
    public RestfulAwareResource deserializeResource(InputStream is) throws ResourceDeserializationException {
	checkResourceDeserializationException();
	return deserializedResourceStub;
    }

    public void setMockHttpPut(HttpPut mockHttpPut) {
	this.mockHttpPut = mockHttpPut;
    }

    public void setMockHttpGet(HttpGet mockHttpGet) {
	this.mockHttpGet = mockHttpGet;
    }

    public void setMockHttpPost(HttpPost mockHttpPost) {
	this.mockHttpPost = mockHttpPost;
    }

    public void setMockHttpDelete(HttpDelete mockHttpDelete) {
	this.mockHttpDelete = mockHttpDelete;
    }

    private void checkResourceRequestCreationException() throws ResourceRequestCreationException {
	if (exceptionMessageStub != null)
	    throw new ResourceRequestCreationException(exceptionMessageStub);

    }
    
    private void checkResourceDeserializationException() throws ResourceDeserializationException {
	if (resourceDeserializationExceptionMessageStub != null)
	    throw new ResourceDeserializationException(resourceDeserializationExceptionMessageStub);
    }

    @Override
    public HttpGet createReadResourceRequest(RestfulAwareResource resource) throws ResourceRequestCreationException {
	checkResourceRequestCreationException();
	return mockHttpGet;
    }

    @Override
    public HttpPut createCreateResourceCollectionRequest(RestfulAwareResource resource) throws ResourceRequestCreationException {
	checkResourceRequestCreationException();
	return mockHttpPut;
    }

    @Override
    public HttpPut createCreateResourceRequest(RestfulAwareResource resource) throws ResourceRequestCreationException {
	checkResourceRequestCreationException();
	return mockHttpPut;

    }

    @Override
    public HttpPost createUpdateResourceCollectionRequest(RestfulAwareResource resource) throws ResourceRequestCreationException {
	checkResourceRequestCreationException();
	return mockHttpPost;
    }

    @Override
    public HttpPost createUpdateResourceRequest(RestfulAwareResource resource) throws ResourceRequestCreationException {
	checkResourceRequestCreationException();
	return mockHttpPost;
    }

    @Override
    public HttpDelete createDeleteResourceCollectionRequest(RestfulAwareResource resource) throws ResourceRequestCreationException {
	checkResourceRequestCreationException();
	return mockHttpDelete;
    }

    @Override
    public HttpDelete createDeleteResourceRequest(RestfulAwareResource resource) throws ResourceRequestCreationException {
	checkResourceRequestCreationException();
	return mockHttpDelete;
    }

}
