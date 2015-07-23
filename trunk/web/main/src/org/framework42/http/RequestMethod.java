package org.framework42.http;

public enum RequestMethod {

    GET, HEAD, POST, PUT, DELETE, CONNECT, OPTIONS, TRACE;

    public static RequestMethod findByName(String name) {

        for(RequestMethod requestMethod: RequestMethod.values()) {

            if(requestMethod.name().equalsIgnoreCase(name)) {

                return requestMethod;
            }
        }

        throw new IllegalArgumentException("No Request Method with name "+name+" Exists!");
    }

}
