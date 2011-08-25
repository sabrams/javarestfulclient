package com.restfulclient;


// todo: distinction between resource and entity

/**
 * Extension of this class and exclusive usage of its methods during interactions with servers
 * will enforce the RESTful principal of 
 * 
 * 
 * From Roy Fielding's dissertation:
 * 
 * Table 5-1. REST Data Elements
 * Data Element | Modern Web Examples
 * -----------------------------------
 * resource | the intended conceptual target of a hypertext reference
 * resource identifier  | URL, URN
 * representation   | HTML document, JPEG image
 * representation metadata  | media type, last-modified time
 * resource metadata    | source link, alternates, vary
 * control data | if-modified-since, cache-control
 * 
 * @author stephenabrams
 *
 */
public abstract class RestfulAwareResource {
    
    // do available content types need to be here?

    private String uri = null;
    private RestfulAwareResource parent = null;
    private String topLevelUri = null;
    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }
    public RestfulAwareResource getParent() {
        return parent;
    }
    public void setParent(RestfulAwareResource parent) {
        this.parent = parent;
    }
    public String getTopLevelUri() {
        return topLevelUri;
    }
    public void setTopLevelUri(String topLevelUri) {
        this.topLevelUri = topLevelUri;
    }
    
    
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((parent == null) ? 0 : parent.hashCode());
	result = prime * result + ((topLevelUri == null) ? 0 : topLevelUri.hashCode());
	result = prime * result + ((uri == null) ? 0 : uri.hashCode());
	return result;
    }
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	RestfulAwareResource other = (RestfulAwareResource) obj;
	if (parent == null) {
	    if (other.parent != null)
		return false;
	} else if (!parent.equals(other.parent))
	    return false;
	if (topLevelUri == null) {
	    if (other.topLevelUri != null)
		return false;
	} else if (!topLevelUri.equals(other.topLevelUri))
	    return false;
	if (uri == null) {
	    if (other.uri != null)
		return false;
	} else if (!uri.equals(other.uri))
	    return false;
	return true;
    }
    
    
    
    
//    private Method uniqueIdentifierMethod = null;
//    private String pathGroupingName = null;

//    public RestfulAwareResource() throws ResourceAnnotationDefinitionException {
//	init();
//    }

//    private void init() throws ResourceAnnotationDefinitionException {
//	try {
//	    pathGroupingName = ((ResourceHierarchicalPathGroupingAnnotation) this.getClass().getAnnotation(ResourceHierarchicalPathGroupingAnnotation.class))
//		    .pathGroupingComponent();
//	} catch (Exception e) {
//	    // do nothing, covered below
//	}
//	for (Method m : this.getClass().getMethods()) {
//	    if (m.isAnnotationPresent(ResourceHierarchicalPathUniqueIdentifierAnnotation.class)) {
//		uniqueIdentifierMethod = m;
//		break; // TODO: check for more than one invocation, throw exception
//	    }
//	}
//	if (pathGroupingName == null)
//	    throw new ResourceAnnotationDefinitionException(this.getClass().getName()
//		    + " has not had a property properly annotated with ResourceHierarchicalPathUniqueIdentifierAnnotation");
//	if (uniqueIdentifierMethod == null)
//	    throw new ResourceAnnotationDefinitionException(this.getClass().getName()
//		    + " has not been properly annotated with ResourceHierarchicalPathGroupingAnnotation");
//
//    }

}
