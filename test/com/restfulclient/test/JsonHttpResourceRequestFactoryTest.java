package com.restfulclient.test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.codehaus.jackson.map.ObjectMapper;

import com.restfulclient.RestfulAwareResource;
import com.restfulclient.exception.ResourceDeserializationException;
import com.restfulclient.restrequestfactory.JsonHttpResourceRequestFactory;

public class JsonHttpResourceRequestFactoryTest extends TestCase {

    final ObjectMapper mapper = new ObjectMapper();
    TestClassToDeserialize example1;
    TestClassToDeserialize example2;
    String example1InJson;
    String example2InJson;
    List<TestClassToDeserialize> list;
    String listInJson;
    
    JsonHttpResourceRequestFactory<TestClassToDeserialize> factoryUnderTest;

    @Override
    protected void setUp() throws Exception {
	factoryUnderTest = new JsonHttpResourceRequestFactory<TestClassToDeserialize>("http://myHost.com", "1234", mapper);
	example1 = new TestClassToDeserialize();
	example1.setBooleanParam(true);
	int[] intArray = { 1, 4, 7, 2, 5 };
	example1.setIntArray(intArray);
	example1.setIntParam(3);
	example1.setStringParam("some string value");

	example1InJson = "{\"intParam\":3,\"booleanParam\":true,\"stringParam\":\"some string value\", \"intArray\":[1, 4, 7, 2, 5]}";
	
	example2 = new TestClassToDeserialize();
	example2.setBooleanParam(false);
	example2.setIntArray(null);
	example2.setIntParam(8);
	example2.setStringParam(null);
	
	example2InJson = "{\"booleanParam\":false, \"intParam\":8}";
	
	list = new ArrayList<JsonHttpResourceRequestFactoryTest.TestClassToDeserialize>();
	list.add(example1);
	list.add(example2);
	
	listInJson = "[".concat(example1InJson).concat(",").concat(example2InJson).concat("]");
	
	super.setUp();
	
    }

    public void testDeserializeResource() throws ResourceDeserializationException, UnsupportedEncodingException {

	InputStream is = new ByteArrayInputStream(example1InJson.getBytes("UTF-8"));

	Object obj = factoryUnderTest.deserializeResource(is);
	assertTrue(obj instanceof TestClassToDeserialize);
	assertEquals(example1, (TestClassToDeserialize)obj);
    }

    public void testDeserializeResourceCollection() throws ResourceDeserializationException, UnsupportedEncodingException {
	InputStream is = new ByteArrayInputStream(listInJson.getBytes("UTF-8"));

	Object obj = factoryUnderTest.deserializeResourceCollection(is);

	assertEquals(list, obj); // TODO list utils form apache commons (?)
	
    }

    public class TestClassToDeserialize extends RestfulAwareResource {
	private int intParam;
	private boolean booleanParam;
	private String stringParam;
	private int[] intArray;

	public int getIntParam() {
	    return intParam;
	}

	public void setIntParam(int intParam) {
	    this.intParam = intParam;
	}

	public boolean isBooleanParam() {
	    return booleanParam;
	}

	public void setBooleanParam(boolean booleanParam) {
	    this.booleanParam = booleanParam;
	}

	public String getStringParam() {
	    return stringParam;
	}

	public void setStringParam(String stringParam) {
	    this.stringParam = stringParam;
	}

	public int[] getIntArray() {
	    return intArray;
	}

	public void setIntArray(int[] intArray) {
	    this.intArray = intArray;
	}

	

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = super.hashCode();
	    result = prime * result + getOuterType().hashCode();
	    result = prime * result + (booleanParam ? 1231 : 1237);
	    result = prime * result + Arrays.hashCode(intArray);
	    result = prime * result + intParam;
	    result = prime * result + ((stringParam == null) ? 0 : stringParam.hashCode());
	    return result;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
		return true;
	    if (!super.equals(obj))
		return false;
	    if (getClass() != obj.getClass())
		return false;
	    TestClassToDeserialize other = (TestClassToDeserialize) obj;
	    if (!getOuterType().equals(other.getOuterType()))
		return false;
	    if (booleanParam != other.booleanParam)
		return false;
	    if (!Arrays.equals(intArray, other.intArray))
		return false;
	    if (intParam != other.intParam)
		return false;
	    if (stringParam == null) {
		if (other.stringParam != null)
		    return false;
	    } else if (!stringParam.equals(other.stringParam))
		return false;
	    return super.equals(obj);
	}

	private JsonHttpResourceRequestFactoryTest getOuterType() {
	    return JsonHttpResourceRequestFactoryTest.this;
	}

    }

}
