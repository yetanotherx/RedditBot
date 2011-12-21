package yetanotherx.redditbot.api.modules;

import java.util.ArrayList;
import java.util.List;
import yetanotherx.redditbot.RedditPlugin;
import yetanotherx.redditbot.api.APIModule;
import yetanotherx.redditbot.api.data.AccountData;
import yetanotherx.redditbot.api.data.SubredditData;
import yetanotherx.redditbot.http.Transport;
import yetanotherx.redditbot.http.request.Request;
import yetanotherx.redditbot.http.request.WebRequest;
import yetanotherx.redditbot.http.response.JSONResult;
import yetanotherx.redditbot.http.response.Response;
import yetanotherx.redditbot.util.HTTPUtils;
import yetanotherx.redditbot.util.MapNode;

/**
 * API module for interacting with users
 * 
 * @author yetanotherx
 */
public class RedditUser extends APIModule {

    protected AccountData user;
    protected boolean isMe = false;
    
    protected RedditUser(RedditPlugin plugin, AccountData user) {
        super(plugin);
        this.user = user;
    }

    /**
     * Creates an instance for the currently logged in user
     * 
     * @param plugin
     * @return 
     */
    public static RedditUser newFromCurrentUser(RedditPlugin plugin) {
        RedditUser user = new RedditUser(plugin, getDataForCurrentUser(plugin));
        user.isMe = true;
        return user;
    }
    
    /**
     * Creates an instance for the given AccountData
     * 
     * @param plugin
     * @param user
     * @return 
     */
    public static RedditUser newFromUser(RedditPlugin plugin, AccountData user) {
        return new RedditUser(plugin, user);
    }
    
    public static RedditUser newFromID(RedditPlugin plugin, String id) {
        throw new UnsupportedOperationException("Reddit API does not support getting info about other users by ID right now.");
    }
    
    /**
     * Creates an instance for this username
     * 
     * @param plugin
     * @param name
     * @return 
     */
    public static RedditUser newFromName(RedditPlugin plugin, String name) {
        return new RedditUser(plugin, getDataForUserName(plugin, name));
    }
    
    protected static AccountData getDataForCurrentUser(RedditPlugin plugin) {
        Transport transport = plugin.getTransport();
        
        Request request = new WebRequest(plugin);
        request.setURL(plugin.getRedditURL() + "/api/me.json");
        transport.setRequest(request);

        Response response = transport.sendURL();
        JSONResult json = response.getJSONResult();

        MapNode localData = json.getMapNode("data");
        if( localData != null ) {
            return AccountData.newInstance(localData);
        }
        return null;
    }
    
    protected static AccountData getDataForUserName(RedditPlugin plugin, String name) {
        Transport transport = plugin.getTransport();
        
        Request request = new WebRequest(plugin);
        request.setURL(plugin.getRedditURL() + "/user/" + HTTPUtils.urlEncode(name) + "/about.json");
        transport.setRequest(request);

        Response response = transport.sendURL();
        JSONResult json = response.getJSONResult();

        MapNode localData = json.getMapNode("data");
        if( localData != null ) {
            return AccountData.newInstance(localData);
        }
        return null;
    }

    /**
     * Returns the AccountData for this user
     * 
     * @return 
     */
    public AccountData getAccountData() {
        return user;
    }
    
    /**
     * Returns a list of SubscriptionData that this user is subscribed to.
     * 
     * @return 
     */
    public List<SubredditData> getSubscriptions() {
        if( !this.isMe ) {
            throw new UnsupportedOperationException("You can only get the subscriptions of your account only.");
        }
        
        Transport transport = plugin.getTransport();
        Request request = new WebRequest(plugin);
        request.setURL(plugin.getRedditURL() + "/api/mine.json");
        transport.setRequest(request);
        
        Response response = transport.sendURL();
        JSONResult json = response.getJSONResult();
        
        List<SubredditData> subs = new ArrayList<SubredditData>();
        
        for( MapNode node : json.getMapNodeList("data/children") ) {
            subs.add(SubredditData.newInstance(node.getMapNode("data")));
        }
        
        return subs;
    }
    
    
}
