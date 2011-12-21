package yetanotherx.redditbot.http;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import yetanotherx.redditbot.RedditPlugin;
import yetanotherx.redditbot.http.request.Request;
import yetanotherx.redditbot.http.response.Response;
import yetanotherx.redditbot.http.response.WebResponse;

public class JSoupTransport extends Transport {

    public JSoupTransport(RedditPlugin plugin) {
        super(plugin);
    }

    @Override
    public Response sendURL() {

        Connection.Response jsoup = null;
        try {
            jsoup = Jsoup.connect(request.getURL()).
                    method(request.getMethod().getJSoup()).
                    data(request.getParameters()).execute();
        } catch (IOException ex) {
            Logger.getLogger(JSoupTransport.class.getName()).log(Level.SEVERE, null, ex);
        }

        Response response = new WebResponse(request.getPlugin());
        
        response.setHTTPCode(jsoup.statusCode());
        response.setContent(jsoup.body());
        response.setContentType(jsoup.contentType());
        
        //TODO: User agent
        
        return response;
    }
}
