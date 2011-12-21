package yetanotherx.redditbot.api.modules;

import java.util.HashMap;
import org.apache.http.cookie.Cookie;
import yetanotherx.redditbot.RedditPlugin;
import yetanotherx.redditbot.api.APIModule;
import yetanotherx.redditbot.exception.APIException;
import yetanotherx.redditbot.exception.NetworkException;
import yetanotherx.redditbot.http.Transport;
import yetanotherx.redditbot.http.request.Request;
import yetanotherx.redditbot.http.request.RequestType;
import yetanotherx.redditbot.http.request.WebRequest;
import yetanotherx.redditbot.http.response.JSONResult;
import yetanotherx.redditbot.http.response.Response;
import yetanotherx.redditbot.util.collections.EasyHashMap;

/**
 * API module for interacting with Reddit itself. Only used for logins.
 * 
 * @author yetanotherx
 */
public class RedditCore extends APIModule {

    protected String username;
    protected String password;

    public RedditCore(RedditPlugin plugin, String username, String password) {
        super(plugin);
        this.username = username;
        this.password = password;
    }
    
    /**
     * Creates a new instance for this username and password
     * @param plugin
     * @param user
     * @param pass
     * @return 
     */
    public static RedditCore newFromUserAndPass(RedditPlugin plugin, String user, String pass) {
        return new RedditCore(plugin, user, pass);
    }

    /**
     * Logs into Reddit. 
     * 
     * Warning! The password field is destroyed after this is called. If
     * you need to log in again, you must initialize this module again.
     * 
     * @return 
     */
    public boolean doLogin() {
        
        Transport transport = plugin.getTransport();

        HashMap<String, String> map = new EasyHashMap<String, String>(
                "user", username,
                "passwd", password,
                "api_type", "json");

        Request request = new WebRequest(plugin, map);
        request.setURL(plugin.getRedditURL() + "/api/login");
        request.setMethod(RequestType.POST);
        transport.setRequest(request);

        Response response = transport.sendURL();
        password = "";
        
        JSONResult json = response.getJSONResult();
        
        if (!json.getStringList("json/errors").isEmpty()) {
            throw new APIException(json.getStringList("json/errors").get(1));
        }
        
        for( Cookie cookie : response.getCookies() ) {
            if( cookie.getName().equals("reddit_session") ) {                
                plugin.setCookie(cookie);
            }
        }
        
        if( plugin.getCookie() == null ) {
            throw new NetworkException("No cookie was found. Check the username and password.");
        }
        
        plugin.setModHash(json.getString("json/data/modhash"));
        
        return true;
    }
    
}
