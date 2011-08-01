package com.restfulclient.test;

import com.restfulclient.RestfulAwareResource;

public class MockBookRestfulAwareResource extends RestfulAwareResource {
    
    String name;
    String description;
    
    public MockBookRestfulAwareResource() {
	
    }
    public MockBookRestfulAwareResource(String name, String description) {
	super();
	this.name = name;
	this.description = description;
    }
    
    

}
