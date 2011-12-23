package com.yetanotherx.reddit.http.request;

/**
 * The different types of HTTP method. Only GET and POST are implemented.
 * All the other types will automatically be sent as POST requests.
 * 
 * @author yetanotherx
 */
public enum RequestType {

    GET,
    POST,
    PUT,
    HEAD,
    DELETE
}
