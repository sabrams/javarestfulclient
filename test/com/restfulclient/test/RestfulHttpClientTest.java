package com.restfulclient.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import junit.framework.TestCase;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import com.restfulclient.RestfulAwareResource;
import com.restfulclient.RestfulHttpClient;
import com.restfulclient.exception.ResourceDeserializationException;
import com.restfulclient.exception.ResourceNotRegisteredException;
import com.restfulclient.exception.ResourceRequestCreationException;
import com.restfulclient.exception.ResourceUriInvalidException;
import com.restfulclient.test.mock.MockBookRestfulAwareResource;
import com.restfulclient.test.mock.MockSerializer;
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

    MockHttpClient mockHttpClient;

    MockSerializer mockSerializer;

    MockHttpEntity mockHttpEntity;

    HttpResponse httpStubResponse;

    // final MockHttpResourceRequestFactory<MockBookRestfulAwareResource>
    // factory = new
    // MockHttpResourceRequestFactory<MockBookRestfulAwareResource>();

    @Override
    protected void setUp() throws Exception {

        final MockBookRestfulAwareResource mockResource = new MockBookRestfulAwareResource(); // todo:

        mockHttpClient = new MockHttpClient();
        mockSerializer = new MockSerializer();
        mockHttpEntity = new MockHttpEntity();
        httpStubResponse = new MockHttpResponse();
        clientUnderTest = new RestfulHttpClient();

        clientUnderTest.setHttpClient(mockHttpClient);
        clientUnderTest.setSerializer(mockSerializer);

        httpStubResponse.setEntity(mockHttpEntity);

        mockHttpClient.setStubHttpResponse(httpStubResponse);
    }

    public void testPosGet() throws ResourceUriInvalidException,
            ResourceRequestCreationException, ResourceDeserializationException,
            ResourceNotRegisteredException, IOException {

        String resourceUri = "http://host.com/books/1";
        RestfulAwareResource book = new MockBookRestfulAwareResource();
        book.setUri(resourceUri);

        // it should execute a get request on the httpClient with the resource's
        // URI
        HttpGet expectedRequest = new HttpGet(resourceUri);

        // it should return a resource equivalent to what was returned on the
        // http client response
        RestfulAwareResource expectedBook = new MockBookRestfulAwareResource(
                "Clam Book", "Book about clams...");
        mockSerializer.setStubResource(expectedBook);

        // it should call the deserializer with the input stream from the http
        // client response
        byte[] mockReturnedContent = new byte[] { 23, 120, -5 };
        InputStream inputStreamStub = new ByteArrayInputStream(
                mockReturnedContent);
        mockHttpEntity.setInputStreamStub(inputStreamStub);

        // Do it, Doug!
        RestfulAwareResource actualBook = clientUnderTest.get(book);

        // verify the external request
        HttpUriRequest actualRequest = mockHttpClient.getLastRequest();
        assertTrue(actualRequest instanceof HttpGet);
        assertEquals(expectedRequest.getURI(), mockHttpClient.getLastRequest()
                .getURI());

        // verify the byte array from the response is what was deserialized
        assertTrue(Arrays.equals(mockReturnedContent,
                mockSerializer.getLastByteArray()));
        // verify the correct resource is returned
        assertEquals(expectedBook, actualBook);

        /*
         * I want to say
         * 
         * client.get(Event.class); // collection of events at top level
         * client.get(event); // event contains its location url (??? best? need
         * it?) client.update(event); // event contains its location url
         * client.delete(event); // event contains its location url
         * client.create(event); // if event has a parent, use parent's location
         * for this resource collection.
         * 
         * client.get(Guest.class, event); // collection of guests for the event
         * (annotation in event can allow this)
         */

    }
}
