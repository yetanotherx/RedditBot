package yetanotherx.redditbot.http;

import java.io.StringWriter;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import yetanotherx.redditbot.RedditPlugin;
import yetanotherx.redditbot.exception.NetworkException;
import yetanotherx.redditbot.http.request.Request;
import yetanotherx.redditbot.http.request.RequestType;
import yetanotherx.redditbot.http.response.Response;
import yetanotherx.redditbot.http.response.WebResponse;

public class ApacheTransport extends Transport {

    public ApacheTransport(RedditPlugin plugin) {
        super(plugin);
    }

    @Override
    public Response sendURL(Request request) throws NetworkException {
        String url = Transport.parseParameters(request);

        HttpClient client = new DefaultHttpClient();

        try {
            HttpResponse response = null;
            
            if (request.getMethod() == RequestType.GET) {
                HttpGet method = new HttpGet(Transport.parseParameters(request));
                response = client.execute(method);
            } else {
                HttpPost method = new HttpPost(Transport.parseParameters(request));
                response = client.execute(method);
            }
            
            StringWriter writer = new StringWriter();
            response.getEntity().getContent();
            IOUtils.copy(response.getEntity().getContent(), writer);

            Response resp = new WebResponse(request.getPlugin());
            resp.setContent(writer.toString());
            resp.setHTTPCode(response.getStatusLine().getStatusCode());
            resp.setContentType(response.getEntity().getContentType().getValue());

            //TODO - Useragent
            return resp;

        } catch (Exception ex) {
            throw new NetworkException("Failed to get URL " + url + " (" + ex.getMessage() + ")");
        }

    }
}
