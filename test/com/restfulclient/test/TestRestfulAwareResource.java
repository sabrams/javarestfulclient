package com.restfulclient.test;

import com.restfulclient.RestfulAwareResource;
import com.restfulclient.test.mock.MockBookRestfulAwareResource;

public class TestRestfulAwareResource {

    /*
     * A restful aware resource must have a parent to be created, or have a top level location (no creation without service?)
     * 
     * Might want to create the resource, but you should already have the location you need to create with
     * 
     * A restful aware resource can not be updated/deleted/retrieved without a location
     * 
     * 
     * 
     */
    
//    public void testRestfulValidCreations() {
//	RestfulAwareResource objectUnderTest = new RestfulAwareResource() {
//	};
//    }
    
    public void testValidGetScenario() {
	RestfulAwareResource objectUnderTest = new MockBookRestfulAwareResource(); 
	
    }
    
    public void testInvalidGetScenario() {
        RestfulAwareResource objectUnderTest = new MockBookRestfulAwareResource();
//        objectUnderTest.g
    }
}
