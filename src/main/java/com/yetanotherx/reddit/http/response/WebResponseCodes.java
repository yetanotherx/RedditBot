package com.yetanotherx.reddit.http.response;

import java.util.HashMap;

/**
 * Static list of all error codes and their meanings
 * 
 * @author yetanotherx
 */
public class WebResponseCodes {

    private final static HashMap<Integer, String> codes = new HashMap<Integer, String>();
    private final static HashMap<String, Integer> reverseCodes = new HashMap<String, Integer>();
    
    static {
        addCode(100, "Continue");
        addCode(101, "Switching Protocols");
        addCode(200, "OK");
        addCode(201, "Created");
        addCode(202, "Accepted");
        addCode(203, "Non-Authoritative Information");
        addCode(204, "No Content");
        addCode(205, "Reset Content");
        addCode(206, "Partial Content");
        addCode(300, "Multiple Choices");
        addCode(301, "Moved Permanently");
        addCode(302, "Found");
        addCode(303, "See Other");
        addCode(304, "Not Modified");
        addCode(305, "Use Proxy");
        addCode(306, "(Unused)");
        addCode(307, "Temporary Redirect");
        addCode(400, "Bad Request");
        addCode(401, "Unauthorized");
        addCode(402, "Payment Required");
        addCode(403, "Forbidden");
        addCode(404, "Not Found");
        addCode(405, "Method Not Allowed");
        addCode(406, "Not Acceptable");
        addCode(407, "Proxy Authentication Required");
        addCode(408, "Request Timeout");
        addCode(409, "Conflict");
        addCode(410, "Gone");
        addCode(411, "Length Required");
        addCode(412, "Precondition Failed");
        addCode(413, "Request Entity Too Large");
        addCode(414, "Request-URI Too Long");
        addCode(415, "Unsupported Media Type");
        addCode(416, "Requested Range Not Satisfiable");
        addCode(417, "Expectation Failed");
        addCode(500, "Internal Server Error");
        addCode(501, "Not Implemented");
        addCode(502, "Bad Gateway");
        addCode(503, "Service Unavailable");
        addCode(504, "Gateway Timeout");
        addCode(505, "HTTP Version Not Supported");
    }
    
    private static void addCode(Integer code, String message) {
        codes.put(code, message);
        reverseCodes.put(message, code);
    }
    
    public static String getError(Integer code) {
        return codes.get(code);
    }
    
    public static Integer getError(String message) {
        return reverseCodes.get(message);
    }
}
