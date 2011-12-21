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

public class Save extends APIModule {

    protected String id;
    protected String modHash;

    public Save(RedditPlugin plugin, String id, String modHash) {
        super(plugin);
        this.id = id;
        this.modHash = modHash;
    }

    @Override
    public void execute() throws RedditException {
        
        Transport transport = plugin.getTransport();

        HashMap<String, String> map = new EasyHashMap<String, String>(
                "id", id,
                "uh", modHash);

        Request request = new WebRequest(plugin, map);
        request.setURL(plugin.getRedditURL() + "/api/save");
        request.setMethod(RequestType.POST);
        transport.setRequest(request);

        Response response = transport.sendURL();
        String error = APIModule.getError(response.getContent());

        if (error != null) {
            throw new APIException(APIError.realValueOf(error).getMessage());
        }
        
    }
    
}
