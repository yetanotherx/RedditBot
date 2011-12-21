package yetanotherx.redditbot.api.module;

import java.util.HashMap;
import yetanotherx.redditbot.RedditPlugin;
import yetanotherx.redditbot.api.APIError;
import yetanotherx.redditbot.api.APIModule;
import yetanotherx.redditbot.exception.APIException;
import yetanotherx.redditbot.exception.RedditException;
import yetanotherx.redditbot.http.Transport;
import yetanotherx.redditbot.http.request.Request;
import yetanotherx.redditbot.http.request.RequestType;
import yetanotherx.redditbot.http.request.WebRequest;
import yetanotherx.redditbot.http.response.Response;
import yetanotherx.redditbot.util.collections.EasyHashMap;

public class Submit extends APIModule {

    protected String title;
    protected String content;
    protected String subreddit;
    protected LinkType type;
    protected String modHash;

    public Submit(RedditPlugin plugin, String title, String content, String subreddit, LinkType type, String modHash) {
        super(plugin);
        this.title = title;
        this.content = content;
        this.subreddit = subreddit;
        this.type = type;
        this.modHash = modHash;
    }

    @Override
    public void execute() throws RedditException {
        
        Transport transport = plugin.getTransport();

        HashMap<String, String> map = new EasyHashMap<String, String>(
                "title", title,
                "sr", subreddit,
                "kind", type.toString(),
                "uh", modHash);

        if( type == LinkType.SELF ) {
            map.put("text", content);
        }
        else {
            map.put("url", content);
        }
        
        Request request = new WebRequest(plugin, map);
        request.setURL(plugin.getRedditURL() + "/api/submit");
        request.setMethod(RequestType.POST);
        transport.setRequest(request);

        Response response = transport.sendURL();
        String error = APIModule.getError(response.getContent());

        if (error != null) {
            throw new APIException(APIError.realValueOf(error).getMessage());
        }
        
    }
    
    public static enum LinkType {
        LINK,
        SELF;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
    
}
