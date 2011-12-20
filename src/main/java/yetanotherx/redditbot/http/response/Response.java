package yetanotherx.redditbot.http.response;

import java.util.Map;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import yetanotherx.redditbot.RedditPlugin;
import yetanotherx.redditbot.exception.ParserException;

public abstract class Response {
    
    protected RedditPlugin plugin;
    protected Integer code;
    protected String contentType;
    protected String content;
    protected JSONResult jsonObject;

    public Response(RedditPlugin plugin) {
        this.plugin = plugin;
    }
    
    @SuppressWarnings("unchecked")
    public JSONResult getJSONResult() throws ParserException {
        if( jsonObject == null ) {
            try {
                jsonObject = new JSONResult();
                jsonObject.setRoot((Map<String, Object>) new JSONParser().parse(content));
            } catch (ParseException ex) {
                throw new ParserException("Could not parse JSON object (" + ex.getMessage() + ")");
            }
        }
        return jsonObject;
    }

    public Integer getHTTPCode() {
        return code;
    }

    public String getContentType() {
        return contentType;
    }

    public void setHTTPCode(Integer code) {
        this.code = code;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
