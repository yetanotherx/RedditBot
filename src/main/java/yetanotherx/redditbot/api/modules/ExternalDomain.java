package yetanotherx.redditbot.api.modules;

import java.util.ArrayList;
import java.util.List;
import yetanotherx.redditbot.RedditPlugin;
import yetanotherx.redditbot.api.APIModule;
import yetanotherx.redditbot.api.data.LinkData;
import yetanotherx.redditbot.http.Transport;
import yetanotherx.redditbot.http.request.Request;
import yetanotherx.redditbot.http.request.WebRequest;
import yetanotherx.redditbot.http.response.JSONResult;
import yetanotherx.redditbot.http.response.Response;
import yetanotherx.redditbot.util.HTTPUtils;
import yetanotherx.redditbot.util.MapNode;

/**
 * API module for getting info about external domains
 * 
 * @author yetanotherx
 */
public class ExternalDomain extends APIModule {

    protected String domain;

    protected ExternalDomain(RedditPlugin plugin, String domain) {
        super(plugin);
        this.domain = domain;
    }
    
    /**
     * Load a new instance for the given domain
     * 
     * @param plugin
     * @param domain
     * @return 
     */
    public static ExternalDomain newFromDomain(RedditPlugin plugin, String domain) {
        return new ExternalDomain(plugin, domain);
    }

    /**
     * Get a list of all links to this domain
     * 
     * @return 
     */
    public List<LinkData> getUsages() {
        Transport transport = plugin.getTransport();
        Request request = new WebRequest(plugin);
        request.setURL(plugin.getRedditURL() + "/domain/" + HTTPUtils.urlEncode(domain) + "/.json");
        transport.setRequest(request);
        
        Response response = transport.sendURL();
        JSONResult json = response.getJSONResult();
        
        ArrayList<LinkData> links = new ArrayList<LinkData>();
        
        for( MapNode node : json.getMapNodeList("data/children") ) {
            links.add(LinkData.newInstance(node.getMapNode("data")));
        }
        
        return links;
    }
    
}
