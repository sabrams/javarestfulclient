package com.restfulclient.test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Locale;

import junit.framework.TestCase;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.params.HttpParams;

import com.restfulclient.RestfulAwareResource;
import com.restfulclient.RestfulHttpClient;
import com.restfulclient.exception.ResourceDeserializationException;
import com.restfulclient.exception.ResourceNotRegisteredException;
import com.restfulclient.exception.ResourceRequestCreationException;
import com.restfulclient.exception.ResourceUriUnknownException;
import com.restfulclient.restrequestfactory.HttpResourceRequestFactory;
import com.restfulclient.test.mock.MockHttpResourceRequestFactory;
import com.restfulclient.test.mock.apachehttp.MockHttpClient;
import com.restfulclient.test.mock.apachehttp.MockHttpEntity;
import com.restfulclient.test.mock.apachehttp.MockHttpResponse;

public class RestfulHttpClientTest extends TestCase {

    /*
     * This is the main interface for HTTP operations to retrieve and modify
     * restful resources.
     * 
     * It should return a populated resource, based on HTTP response, on GET
     * when resource is property configured with a URI. It should throw an
     * exception when the resource is mis-configured. It should throw an
     * exception when a retrieval error occurs
     */
    RestfulHttpClient clientUnderTest;
    MockHttpClient httpClient;
    final MockHttpResourceRequestFactory<MockBookRestfulAwareResource> factory = new MockHttpResourceRequestFactory<MockBookRestfulAwareResource>();

    @Override
    protected void setUp() throws Exception {

	final MockBookRestfulAwareResource mockResource = new MockBookRestfulAwareResource(); // todo:
											      // want
											      // to
											      // just
											      // pass
											      // the
											      // calss

	clientUnderTest = new RestfulHttpClient("host.com", "1234");

	clientUnderTest.registerResource(mockResource.getClass(), factory);
    }

    public void testPosGet() throws ResourceUriUnknownException, ResourceRequestCreationException, ResourceDeserializationException,
	    ResourceNotRegisteredException {
	RestfulAwareResource book = new MockBookRestfulAwareResource();
	book.setUri("/books/1");

	RestfulAwareResource expectedBook = new MockBookRestfulAwareResource("Clam Book", "Book about clams...");

	HttpGet stubRequest = new HttpGet();

	factory.setMockHttpGet(stubRequest);
	factory.setDeserializedResourceStub(expectedBook);

	httpClient = new MockHttpClient();
	HttpResponse httpStubResponse = new MockHttpResponse();
	MockHttpEntity mockHttpEntity = new MockHttpEntity();
	InputStream inputStreamStub = new ByteArrayInputStream(new byte[1]);
	mockHttpEntity.setInputStreamStub(inputStreamStub);
	httpStubResponse.setEntity(mockHttpEntity);
	httpClient.setStubHttpResponse(httpStubResponse);
	clientUnderTest.setHttpClient(httpClient);
	RestfulAwareResource actualBook = clientUnderTest.get(book);

	assertEquals("client did not execute with factory provided request", stubRequest, httpClient.getLastRequest());
	assertEquals("expected book was not returned...", expectedBook, actualBook);
	
	/*
	 * I want to say
	 * 
	 * client.get(Event.class); // collection of events at top level
	 * client.get(event); // event contains its location url (??? best? need it?)
	 * client.update(event); // event contains its location url
	 * client.delete(event); // event contains its location url
	 * client.create(event); // if event has a parent, use parent's location for this resource collection. 
	 * 
	 * client.get(Guest.class, event); // collection of guests for the event (annotation in event can allow this)
	 * 
	 */

    }

}
