package yetanotherx.redditbot.api.module;

import yetanotherx.redditbot.RedditPlugin;
import yetanotherx.redditbot.api.APIModule;
import yetanotherx.redditbot.exception.NetworkException;
import yetanotherx.redditbot.exception.ParserException;
import yetanotherx.redditbot.http.Transport;
import yetanotherx.redditbot.http.request.Request;
import yetanotherx.redditbot.http.request.WebRequest;
import yetanotherx.redditbot.http.response.Response;

public class Domain extends APIModule {
    
    protected String domain;

    public Domain(RedditPlugin plugin, String domain) {
        super(plugin);
        this.domain = domain;
    }

    @Override
    public void execute() throws NetworkException, ParserException {
        Transport transport = plugin.getTransport();
        Request request = new WebRequest(plugin);
        request.setURL("http://www.reddit.com/domain/" + Transport.urlEncode(domain) + "/.json");
        transport.setRequest(request);
        
        Response response = transport.sendURL();
        //System.out.println(response.getJSONResult().getString("data.modhash"));
    }
    
}
