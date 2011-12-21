package yetanotherx.redditbot.api.module;

import java.util.ArrayList;
import yetanotherx.redditbot.RedditPlugin;
import yetanotherx.redditbot.api.APIModule;
import yetanotherx.redditbot.api.types.Link;
import yetanotherx.redditbot.exception.RedditException;
import yetanotherx.redditbot.http.Transport;
import yetanotherx.redditbot.http.request.Request;
import yetanotherx.redditbot.http.request.WebRequest;
import yetanotherx.redditbot.http.response.JSONResult;
import yetanotherx.redditbot.http.response.Response;
import yetanotherx.redditbot.util.MapNode;

public class Domain extends APIModule {
    
    protected String domain;
    protected String modHash;
    protected ArrayList<Link> links;

    public Domain(RedditPlugin plugin, String domain) {
        super(plugin);
        this.domain = domain;
    }

    @Override
    public void execute() throws RedditException {
        Transport transport = plugin.getTransport();
        Request request = new WebRequest(plugin);
        request.setURL("http://www.reddit.com/domain/" + Transport.urlEncode(domain) + "/.json");
        transport.setRequest(request);
        
        Response response = transport.sendURL();
        JSONResult json = response.getJSONResult();
        
        this.modHash = json.getString("data/modhash");
        this.links = new ArrayList<Link>();
        
        for( MapNode node : json.getMapNodeList("data/children") ) {
            this.links.add(Link.newInstance(node.getMapNode("data")));
        }
    }
    
}
