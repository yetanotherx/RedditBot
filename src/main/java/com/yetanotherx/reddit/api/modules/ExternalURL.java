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
 * API module for getting data about external links
 * 
 * @author yetanotherx
 */
public class ExternalURL extends APIModule {

    protected String url;

    protected ExternalURL(RedditPlugin plugin, String url) {
        super(plugin);
        this.url = url;
    }
    
    /**
     * Loads a new instance for the given URL
     * 
     * @param plugin
     * @param url
     * @return 
     */
    public static ExternalURL newFromURL(RedditPlugin plugin, String url) {
        return new ExternalURL(plugin, url);
    }

    /**
     * Find all the places this link was submitted
     * 
     * @return 
     */
    public List<LinkData> getUsages() {
        Transport transport = plugin.getTransport();
        Request request = new WebRequest(plugin);
        request.setURL(plugin.getRedditURL() + "/api/info.json?url=" + HTTPUtils.urlEncode(url));
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
