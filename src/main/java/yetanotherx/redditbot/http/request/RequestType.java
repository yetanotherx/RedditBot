package yetanotherx.redditbot.http.request;

import org.jsoup.Connection;

public enum RequestType {
    
    GET(Connection.Method.GET),
    POST(Connection.Method.POST),
    PUT(Connection.Method.POST),
    HEAD(Connection.Method.POST),
    DELETE(Connection.Method.POST);
    
    protected Connection.Method jSoup;

    private RequestType(Connection.Method jSoup) {
        this.jSoup = jSoup;
    }

    public Connection.Method getJSoup() {
        return jSoup;
    }
    
}
