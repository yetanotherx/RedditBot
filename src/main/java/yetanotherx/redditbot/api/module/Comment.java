package yetanotherx.redditbot.api.module;

import yetanotherx.redditbot.RedditPlugin;
import yetanotherx.redditbot.api.APIError;
import yetanotherx.redditbot.api.APIModule;
import yetanotherx.redditbot.exception.NetworkException;
import yetanotherx.redditbot.exception.ParserException;
import yetanotherx.redditbot.http.Transport;
import yetanotherx.redditbot.http.request.Request;
import yetanotherx.redditbot.http.request.RequestType;
import yetanotherx.redditbot.http.request.WebRequest;
import yetanotherx.redditbot.http.response.JSONResult;
import yetanotherx.redditbot.http.response.Response;
import yetanotherx.redditbot.util.collections.EasyHashMap;

public class Comment extends APIModule {

    protected String parent;
    protected String text;
    protected String modhash;

    public Comment(RedditPlugin plugin, String parent, String text, String modhash) {
        super(plugin);
        this.parent = parent;
        this.text = text;
        this.modhash = modhash;
    }

    @Override
    public void execute() throws NetworkException, ParserException {
        
        Transport transport = plugin.getTransport();
        Request request = new WebRequest(plugin, new EasyHashMap<String, String>("parent", parent, "text", text, "uh", modhash) );
        request.setURL(plugin.getRedditURL() + "/api/comment");
        request.setMethod(RequestType.POST);
        transport.setRequest(request);
        
        Response response = transport.sendURL();
        JSONResult json = response.getJSONResult();
        String error = APIModule.getError(response.getContent());
        
        if( error != null ) {
            throw new APIException(APIError.valueOf(error).getMessage());
        }
        
    }
    
}
