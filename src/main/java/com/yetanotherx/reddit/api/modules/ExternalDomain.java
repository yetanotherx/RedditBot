package com.yetanotherx.reddit.api.modules;

import java.util.ArrayList;
import java.util.List;
import com.yetanotherx.reddit.RedditPlugin;
import com.yetanotherx.reddit.api.APIModule;
import com.yetanotherx.reddit.api.data.LinkData;
import com.yetanotherx.reddit.redditbot.http.Transport;
import com.yetanotherx.reddit.http.request.Request;
import com.yetanotherx.reddit.http.request.WebRequest;
import com.yetanotherx.reddit.http.response.JSONResult;
import com.yetanotherx.reddit.http.response.Response;
import com.yetanotherx.reddit.util.HTTPUtils;
import com.yetanotherx.reddit.util.MapNode;

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
