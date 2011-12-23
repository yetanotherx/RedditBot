package com.yetanotherx.reddit.http.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.cookie.Cookie;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.yetanotherx.reddit.RedditPlugin;
import com.yetanotherx.reddit.exception.ParserException;

/**
 * Core Response class. Stores response headers, error code, content,
 * content type, cookies, and anything else that comes out of the request.
 * 
 * @author yetanotherx
 */
public abstract class Response {

    protected RedditPlugin plugin;
    protected List<Header> headers;
    protected HTTPCode code;
    protected String contentType;
    protected Long contentLength;
    protected String content;
    protected JSONResult jsonObject;
    protected List<Cookie> cookies;

    public Response(RedditPlugin plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("unchecked")
    public JSONResult getJSONResult() {
        if (jsonObject == null) {
            try {
                jsonObject = new JSONResult();
                Object parsed = new JSONParser().parse(content);

                if (parsed instanceof JSONArray) {
                    JSONArray arr = (JSONArray) parsed;
                    HashMap<String, Object> newMap = new HashMap<String, Object>();
                    
                    Integer i = 0;
                    for( Object obj : arr ) {
                        newMap.put(i.toString(), obj);
                        i++;
                    }
                    jsonObject.setBase(newMap);
                } else if (parsed instanceof JSONObject) {
                    jsonObject.setBase((Map<String, Object>) parsed);
                } else {
                    jsonObject = null;
                }

            } catch (ParseException ex) {
                throw new ParserException("Could not parse JSON object (" + ex.getMessage() + ")");
            }
        }
        return jsonObject;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    public HTTPCode getHTTPCode() {
        return code;
    }

    public void setHTTPCode(HTTPCode code) {
        this.code = code;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getContentLength() {
        return contentLength;
    }

    public void setContentLength(Long contentLength) {
        this.contentLength = contentLength;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public List<Cookie> getCookies() {
        return cookies;
    }

    public void setCookies(List<Cookie> cookies) {
        this.cookies = cookies;
    }
}
