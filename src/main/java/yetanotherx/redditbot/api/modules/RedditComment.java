package yetanotherx.redditbot.api.modules;

import java.util.HashMap;
import yetanotherx.redditbot.RedditPlugin;
import yetanotherx.redditbot.api.APIError;
import yetanotherx.redditbot.api.APIModule;
import yetanotherx.redditbot.api.data.CommentData;
import yetanotherx.redditbot.exception.APIException;
import yetanotherx.redditbot.http.Transport;
import yetanotherx.redditbot.http.request.Request;
import yetanotherx.redditbot.http.request.RequestType;
import yetanotherx.redditbot.http.request.WebRequest;
import yetanotherx.redditbot.http.response.Response;
import yetanotherx.redditbot.util.MapNode;
import yetanotherx.redditbot.util.VoteType;
import yetanotherx.redditbot.util.collections.EasyHashMap;

/**
 * API module for interacting with comments
 * 
 * @author yetanotherx
 */
public class RedditComment extends APIModule {

    protected CommentData comment;
    
    protected RedditComment(RedditPlugin plugin, CommentData comment) {
        super(plugin);
        this.comment = comment;
    }

    /**
     * Loads a new instance for this CommentData instance
     * 
     * @param plugin
     * @param comment
     * @return 
     */
    public static RedditComment newFromComment(RedditPlugin plugin, CommentData comment) {
        return new RedditComment(plugin, comment);
    }
    
    /**
     * Loads a new instance for this comment. Link id is the fullname of the link, without
     * the t3_ prefix. Comment id is the fullname of the comment, without the t1_ prefix.
     * 
     * @param plugin
     * @param link
     * @param comment
     * @return 
     */
    public static RedditComment newFromLinkAndCommentIDs(RedditPlugin plugin, String link, String comment) {
        return new RedditComment(plugin, getCommentFromLinkAndCommentIDs(plugin, link, comment));
    }
    
    protected static CommentData getCommentFromLinkAndCommentIDs(RedditPlugin plugin, String link, String comment) {
        
        Transport transport = plugin.getTransport();
        Request request = new WebRequest(plugin);
        
        StringBuilder url = new StringBuilder();
        url.append(plugin.getRedditURL()).append("/comments/").append(link).append("/anything/").append(comment).append(".json");
        request.setURL(url.toString());
        
        transport.setRequest(request);
        
        Response response = transport.sendURL();
        MapNode json = response.getJSONResult();
        
        if( json == null ) {
            return null;
        }
        
        json = json.getMapNode("1");
        
        if( json == null ) {
            return null;
        }
        
        for( MapNode node : json.getMapNodeList("data/children") ) {
            return CommentData.newInstance(node.getMapNode("data"));
        }
        return null;
    }
    
    /**
     * Returns the CommentData instance
     * 
     * @return 
     */
    public CommentData getCommentData() {
        return comment;
    }
    
    /**
     * Posts a reply to the comment
     * 
     * @param text
     * @return 
     */
    public boolean doReply(String text) {
        Transport transport = plugin.getTransport();

        HashMap<String, String> map = new EasyHashMap<String, String>(
                "parent", comment.getFullname(),
                "text", text,
                "uh", plugin.getModHash());

        Request request = new WebRequest(plugin, map);
        request.setURL(plugin.getRedditURL() + "/api/comment");
        request.setMethod(RequestType.POST);
        transport.setRequest(request);

        Response response = transport.sendURL();
        String error = APIModule.getError(response.getContent());

        if (error != null) {
            throw new APIException(APIError.realValueOf(error).getMessage());
        }
        
        return true;
    }
    
    /**
     * Votes on the comment
     * 
     * @param type
     * @return 
     */
    public boolean doVote(VoteType type) {
        Transport transport = plugin.getTransport();

        HashMap<String, String> map = new EasyHashMap<String, String>(
                "id", comment.getFullname(),
                "dir", type.toString(),
                "uh", plugin.getModHash());

        Request request = new WebRequest(plugin, map);
        request.setURL(plugin.getRedditURL() + "/api/vote");
        request.setMethod(RequestType.POST);
        transport.setRequest(request);

        Response response = transport.sendURL();
        String error = APIModule.getError(response.getContent());

        if (error != null) {
            throw new APIException(APIError.realValueOf(error).getMessage());
        }
        
        return true;
    }
    
}
