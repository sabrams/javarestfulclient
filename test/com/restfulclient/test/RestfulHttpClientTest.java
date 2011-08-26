package com.restfulclient.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

import org.apache.http.conn.scheme.Scheme;
import org.junit.After;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;
import junit.framework.TestCase;

import com.restfulclient.RestfulAwareResource;
import com.restfulclient.RestfulHttpClient;
import com.restfulclient.exception.ResourceDeserializationException;
import com.restfulclient.exception.ResourceNotRegisteredException;
import com.restfulclient.exception.ResourceRequestCreationException;
import com.restfulclient.exception.ResourceUriInvalidException;
import com.restfulclient.test.mock.MockBookRestfulAwareResource;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class RestfulHttpClientTest extends TestCase {

    RestfulHttpClient clientUnderTest;

    HttpServer server;

    TestHandler handler;

    String host = "localhost";

    int port = 8082;

    String protocol = "http";

    @Override
    protected void setUp() throws Exception {

        handler = new TestHandler();
        clientUnderTest = new RestfulHttpClient();
        server = HttpServer.create(new InetSocketAddress(port), 5);

        server.createContext("/books", handler);

        server.setExecutor(null); // creates a default executor

        server.start();
    }

    @Override
    protected void tearDown() throws Exception {
        server.stop(0);
        server = null;

    }

    private String createUrl(String path) throws MalformedURLException {
        return protocol + "://" + host + ":" + port + path;
    }

    /*
     * It should send correct request method to server based on client method
     * called It should send content type as represented// TODO: accepts headers
     * It should call the correct URI for the resource It should return a object
     * representing a proper deserialization of the response entity
     */
    public void testPosGet() throws ResourceUriInvalidException,
            ResourceRequestCreationException, ResourceDeserializationException,
            ResourceNotRegisteredException, IOException {

        // set up input
        // TODO scheme/host/etc consumed when chosing the handler, just checking
        // path
        String resourceUriPath = "/books/1";
        String url = createUrl(resourceUriPath);
        RestfulAwareResource book = new MockBookRestfulAwareResource();
        book.setUri(url);

        // set up expected output
        RestfulAwareResource expectedBook = new MockBookRestfulAwareResource(
                "Clam Book", "Book about clams...");

        // configure mock service response
        handler.setResponseBody("{\"name\" : \"Clam Book\", \"description\" : \"Book about clams...\"}");
        handler.setResponseContentType("application/json");

        // execute
        RestfulAwareResource actualBook = clientUnderTest.get(book);

        // names are case sensitive
        assertEquals("GET", handler.getRequestMethod());
        assertEquals(resourceUriPath, handler.getRequestURI());

        assertEquals(expectedBook, actualBook);

    }

    /*
     * It should send correct request method to server based on client method
     * called. It should send content type as represented// TODO: accepts
     * headers. It should call the correct URI for the resource It should return
     * a object representing a proper deserialization of the response entity
     */
    public void testPosPut() throws ResourceUriInvalidException,
            ResourceRequestCreationException, ResourceDeserializationException,
            ResourceNotRegisteredException, IOException {

        handler.setExtractRequestBody(true);
        // set up input
        // TODO scheme/host/etc consumed when chosing the handler, just checking
        // path
        String resourceUriPath = "/books/1";
        String url = createUrl(resourceUriPath);
        RestfulAwareResource desiredResourceStateOnServer = new MockBookRestfulAwareResource(
                "Clam Book", "Book about clams...");
        desiredResourceStateOnServer.setUri(url);
        desiredResourceStateOnServer.setContentType("application/json");

        // execute
        clientUnderTest.put(desiredResourceStateOnServer);

        // names are case sensitive
        assertEquals("PUT", handler.getRequestMethod());
        assertEquals(resourceUriPath, handler.getRequestURI());

        // TODO: where should content type reside? (definitely should not be in
        // 'to string' output)
        // TODO: better handling of the request body here
        String expectedRequestBody = "{\"name\":\"Clam Book\",\"description\":\"Book about clams...\",\"parent\":null,\"contentType\":\"application/json\",\"uri\":\"http://localhost:8082/books/1\",\"topLevelUri\":null}";
        assertEquals(expectedRequestBody, handler.getRequestBody());

    }

    // RestfulAwareResource updateRepresentation = new
    // MockBookRestfulAwareResource(
    // "Clam Book", "Book about clams...");
    //
    // // configure mock service request expectations
    // handler.setRequestMethod("UPDATE");
    // handler.setRequestUri("/books/1");
    //
    // handler.setRequestBody("fdsfddfs");
    //
    // // execute
    // clientUnderTest.put(book);
    //
    // // anything else?
    //
    // }

    class TestHandler implements HttpHandler {

        private String responseContentType = null;

        private String responseBody = null;

        private String resultRequestMethod = null;

        private String requestUri = null;

        private String requestBody = null;

        private boolean extractRequestBody = false;

        // private InputStream resultRequestBody = null;

        void setResponseContentType(String responseContentType) {
            this.responseContentType = responseContentType;
        }

        String getRequestBody() {
            return this.requestBody;
        }

        String getRequestURI() {
            return requestUri;
        }

        String getRequestMethod() {
            return resultRequestMethod;
        }

        void setExtractRequestBody(boolean extractRequestBody) {
            this.extractRequestBody = extractRequestBody;
        }

        // InputStream getRequestBody() {
        // return resultRequestBody;
        // }

        void setResponseBody(String responseBody) {
            this.responseBody = responseBody;
        }

        @Override
        public void handle(HttpExchange t) throws IOException {

            resultRequestMethod = t.getRequestMethod();
            
            

//            if (extractRequestBody) {
//                InputStream in = t.getRequestBody();
//                StringBuffer out = new StringBuffer();
//                byte[] b = new byte[4096];
//                for (int n; (n = in.read(b)) != -1;) {
//                    out.append(new String(b, 0, n));
//                }
//
//                requestBody = out.toString();
//                t.getRequestBody().reset();
//            }

            requestUri = t.getRequestURI().toString();

            Map<String, List<String>> headers = t.getResponseHeaders();
            List<String> contentTypes = new ArrayList<String>();
            contentTypes.add(responseContentType);

            headers.put("Content-Type", contentTypes);

            t.sendResponseHeaders(200, responseBody.length());
            OutputStream os = t.getResponseBody();
            os.write(responseBody.getBytes());
            os.close();
        }
    }

}
