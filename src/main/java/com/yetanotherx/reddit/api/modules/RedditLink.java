package com.yetanotherx.reddit.api.modules;

import java.util.ArrayList;
import java.util.HashMap;
import com.yetanotherx.reddit.RedditPlugin;
import com.yetanotherx.reddit.api.APIError;
import com.yetanotherx.reddit.api.APIModule;
import com.yetanotherx.reddit.api.data.CommentData;
import com.yetanotherx.reddit.api.data.LinkData;
import com.yetanotherx.reddit.exception.APIException;
import com.yetanotherx.reddit.redditbot.http.Transport;
import com.yetanotherx.reddit.http.request.Request;
import com.yetanotherx.reddit.http.request.RequestType;
import com.yetanotherx.reddit.http.request.WebRequest;
import com.yetanotherx.reddit.http.response.JSONResult;
import com.yetanotherx.reddit.http.response.Response;
import com.yetanotherx.reddit.util.LinkType;
import com.yetanotherx.reddit.util.MapNode;
import com.yetanotherx.reddit.util.VoteType;
import com.yetanotherx.reddit.util.collections.EasyHashMap;

/**
 * API module for interacting with internal Reddit links. 
 * (AKA the comment page)
 * 
 * @author yetanotherx
 */
public class RedditLink extends APIModule {

    protected LinkData link;

    protected RedditLink(RedditPlugin plugin, LinkData link) {
        super(plugin);
        this.link = link;
    }

    /**
     * Creates a new instance for this LinkData instance
     * 
     * @param plugin
     * @param link
     * @return 
     */
    public static RedditLink newFromLink(RedditPlugin plugin, LinkData link) {
        return new RedditLink(plugin, link);
    }

    /**
     * Creates a new instance for this link id (without t3_ prefix)
     * 
     * @param plugin
     * @param id
     * @return 
     */
    public static RedditLink newFromID(RedditPlugin plugin, String id) {
        return new RedditLink(plugin, getLinkFromID(plugin, id));
    }

    protected static LinkData getLinkFromID(RedditPlugin plugin, String id) {
        Transport transport = plugin.getTransport();
        Request request = new WebRequest(plugin);
        request.setURL(plugin.getRedditURL() + "/by_id/t3_" + id + ".json");
        transport.setRequest(request);

        Response response = transport.sendURL();
        JSONResult json = response.getJSONResult();

        for (MapNode node : json.getMapNodeList("data/children")) {
            return LinkData.newInstance(node.getMapNode("data"));
        }
        return null;
    }

    /**
     * Returns the LinkData instance
     * @return 
     */
    public LinkData getLinkData() {
        return link;
    }

    /**
     * Get a list of all the comments. This is not recursive.
     * 
     * @return 
     */
    public CommentData[] getComments() {
        Transport transport = plugin.getTransport();
        Request request = new WebRequest(plugin);
        request.setURL(plugin.getRedditURL() + "/comments/" + this.link.getID() + ".json");
        transport.setRequest(request);

        Response response = null;
        try {
            response = transport.sendURL();
            JSONResult json = response.getJSONResult();

            ArrayList<CommentData> data = new ArrayList<CommentData>();
            for (MapNode node : json.getMapNodeList("1/data/children")) {

                data.add(CommentData.newInstance(node.getMapNode("data")));

            }
            return data.toArray(new CommentData[data.size()]);
        } catch (Exception e) {
            e.printStackTrace();
            if (response != null) {
                System.out.println(response.getContent());
            }
            //TODO: Fails?
        }
        return null;

    }

    /**
     * Submits a new link.
     * 
     * @param plugin
     * @param title
     * @param content
     * @param subreddit
     * @param type
     * @return 
     */
    public static boolean doSubmit(RedditPlugin plugin, String title, String content, String subreddit, LinkType type) {

        Transport transport = plugin.getTransport();

        HashMap<String, String> map = new EasyHashMap<String, String>(
                "title", title,
                "sr", subreddit,
                "kind", type.toString(),
                "uh", plugin.getModHash());

        if (type == LinkType.SELF) {
            map.put("text", content);
        } else {
            map.put("url", content);
        }

        Request request = new WebRequest(plugin, map);
        request.setURL(plugin.getRedditURL() + "/api/submit");
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
     * Reply to a link.
     * 
     * @param text
     * @return 
     */
    public boolean doReply(String text) {
        Transport transport = plugin.getTransport();

        HashMap<String, String> map = new EasyHashMap<String, String>(
                "parent", link.getFullname(),
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
     * Saves the link for later.
     * 
     * @return 
     */
    public boolean doSave() {
        Transport transport = plugin.getTransport();

        HashMap<String, String> map = new EasyHashMap<String, String>(
                "id", link.getFullname(),
                "uh", plugin.getModHash());

        Request request = new WebRequest(plugin, map);
        request.setURL(plugin.getRedditURL() + "/api/save");
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
     * Unsaves the link.
     * 
     * @return 
     */
    public boolean doUnsave() {
        Transport transport = plugin.getTransport();

        HashMap<String, String> map = new EasyHashMap<String, String>(
                "id", link.getFullname(),
                "uh", plugin.getModHash());

        Request request = new WebRequest(plugin, map);
        request.setURL(plugin.getRedditURL() + "/api/unsave");
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
     * Hides the link. Not really useful for an API, but it does
     * mark them as hidden in RedditLink.
     * 
     * @return 
     */
    public boolean doHide() {
        Transport transport = plugin.getTransport();

        HashMap<String, String> map = new EasyHashMap<String, String>(
                "id", link.getFullname(),
                "uh", plugin.getModHash());

        Request request = new WebRequest(plugin, map);
        request.setURL(plugin.getRedditURL() + "/api/hide");
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
     * Unhides the link.
     * 
     * @return 
     */
    public boolean doUnhide() {
        Transport transport = plugin.getTransport();

        HashMap<String, String> map = new EasyHashMap<String, String>(
                "id", link.getFullname(),
                "uh", plugin.getModHash());

        Request request = new WebRequest(plugin, map);
        request.setURL(plugin.getRedditURL() + "/api/unhide");
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
     * Votes on the link.
     * 
     * @param type
     * @return 
     */
    public boolean doVote(VoteType type) {
        Transport transport = plugin.getTransport();

        HashMap<String, String> map = new EasyHashMap<String, String>(
                "id", link.getFullname(),
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
