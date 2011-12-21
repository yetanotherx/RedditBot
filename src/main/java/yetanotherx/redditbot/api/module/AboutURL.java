package yetanotherx.redditbot.api.module;

import java.util.ArrayList;
import java.util.List;
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

public class AboutURL extends APIModule {

    protected String url;
    protected String modHash;
    protected List<Link> links;
    
    public AboutURL(RedditPlugin plugin, String url) {
        super(plugin);
        this.url = url;
    }

    @Override
    public void execute() throws RedditException {
        Transport transport = plugin.getTransport();
        Request request = new WebRequest(plugin);
        request.setURL(plugin.getRedditURL() + "/api/info.json?url=" + Transport.urlEncode(url));
        transport.setRequest(request);
        
        Response response = transport.sendURL();
        JSONResult json = response.getJSONResult();
        
        this.modHash = json.getString("data/modhash");
        this.links = new ArrayList<Link>();
        
        for( MapNode node : json.getMapNodeList("data/children") ) {
            this.links.add(Link.newInstance(node.getMapNode("data")));
        }
        
    }

    public String getURL() {
        return url;
    }

    public void setURL(String url) {
        this.url = url;
    }

    public List<Link> getLinks() {
        return links;
    }

    public String getModHash() {
        return modHash;
    }
    
}
