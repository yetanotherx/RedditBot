package yetanotherx.redditbot.api.module;

import java.util.ArrayList;
import java.util.List;
import yetanotherx.redditbot.RedditPlugin;
import yetanotherx.redditbot.api.APIModule;
import yetanotherx.redditbot.api.types.Link;
import yetanotherx.redditbot.api.types.Subreddit;
import yetanotherx.redditbot.exception.RedditException;
import yetanotherx.redditbot.http.Transport;
import yetanotherx.redditbot.http.request.Request;
import yetanotherx.redditbot.http.request.WebRequest;
import yetanotherx.redditbot.http.response.JSONResult;
import yetanotherx.redditbot.http.response.Response;
import yetanotherx.redditbot.util.MapNode;

public class Subscriptions extends APIModule {

    protected String modHash;
    protected List<Subreddit> subs;
    
    public Subscriptions(RedditPlugin plugin) {
        super(plugin);
    }

    @Override
    public void execute() throws RedditException {
        
        Transport transport = plugin.getTransport();
        Request request = new WebRequest(plugin);
        request.setURL(plugin.getRedditURL() + "/api/mine.json");
        transport.setRequest(request);
        
        Response response = transport.sendURL();
        JSONResult json = response.getJSONResult();
        
        this.modHash = json.getString("data/modhash");
        this.subs = new ArrayList<Subreddit>();
        
        for( MapNode node : json.getMapNodeList("data/children") ) {
            this.subs.add(Subreddit.newInstance(node.getMapNode("data")));
        }
        
    }
    
}
