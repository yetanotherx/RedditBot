package yetanotherx.redditbot.api.module;

import yetanotherx.redditbot.RedditPlugin;
import yetanotherx.redditbot.api.APIError;
import yetanotherx.redditbot.api.APIModule;
import yetanotherx.redditbot.exception.APIException;
import yetanotherx.redditbot.exception.RedditException;
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
    public void execute() throws RedditException {
        
        Transport transport = plugin.getTransport();
        Request request = new WebRequest(plugin, new EasyHashMap<String, String>("parent", parent, "text", text, "uh", modhash) );
        request.setURL(plugin.getRedditURL() + "/api/comment");
        request.setMethod(RequestType.POST);
        transport.setRequest(request);
        
        Response response = transport.sendURL();
        String error = APIModule.getError(response.getContent());
        
        if( error != null ) {
            throw new APIException(APIError.realValueOf(error).getMessage());
        }
        
    }
    
}
