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
