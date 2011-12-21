package yetanotherx.redditbot.api.module;

import java.util.HashMap;
import yetanotherx.redditbot.RedditPlugin;
import yetanotherx.redditbot.api.APIError;
import yetanotherx.redditbot.api.APIModule;
import yetanotherx.redditbot.exception.APIException;
import yetanotherx.redditbot.exception.RedditException;
import yetanotherx.redditbot.http.Transport;
import yetanotherx.redditbot.http.request.Request;
import yetanotherx.redditbot.http.request.RequestType;
import yetanotherx.redditbot.http.request.WebRequest;
import yetanotherx.redditbot.http.response.Response;
import yetanotherx.redditbot.util.collections.EasyHashMap;

public class Vote extends APIModule {

    protected String id;
    protected String modHash;
    protected VoteType type;

    public Vote(RedditPlugin plugin, String id, VoteType type, String modHash) {
        super(plugin);
        this.id = id;
        this.modHash = modHash;
        this.type = type;
    }

    @Override
    public void execute() throws RedditException {
        
        Transport transport = plugin.getTransport();

        HashMap<String, String> map = new EasyHashMap<String, String>(
                "id", id,
                "dir", type.toString(),
                "uh", modHash);

        Request request = new WebRequest(plugin, map);
        request.setURL(plugin.getRedditURL() + "/api/vote");
        request.setMethod(RequestType.POST);
        transport.setRequest(request);

        Response response = transport.sendURL();
        String error = APIModule.getError(response.getContent());

        if (error != null) {
            throw new APIException(APIError.realValueOf(error).getMessage());
        }
        
    }
    
    public static enum VoteType {
        UPVOTE(1),
        NOVOTE(0),
        DOWNVOTE(-1);
        
        protected Integer value;

        private VoteType(int value) {
            this.value = value;
        }

        public String toString() {
            return value.toString();
        }
    }
    
}
