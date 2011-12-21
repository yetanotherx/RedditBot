package yetanotherx.redditbot.http;

import com.google.common.io.CharStreams;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpProtocolParams;
import yetanotherx.redditbot.RedditPlugin;
import yetanotherx.redditbot.exception.NetworkException;
import yetanotherx.redditbot.http.request.RequestType;
import yetanotherx.redditbot.http.response.HTTPCode;
import yetanotherx.redditbot.http.response.Response;
import yetanotherx.redditbot.http.response.WebResponse;
import yetanotherx.redditbot.util.HTTPUtils;

/**
 * Instance of Transport that uses Apache HTTPComponents.
 * 
 * @author yetanotherx
 */
public class ApacheTransport extends Transport {

    public ApacheTransport(RedditPlugin plugin) {
        super(plugin);
    }

    @Override
    public Response sendURL() {

        DefaultHttpClient client = new DefaultHttpClient();
        client.getParams().setParameter(HttpProtocolParams.USER_AGENT, plugin.getUserAgent());
        client.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, true);
        client.getParams().setParameter(ClientPNames.MAX_REDIRECTS, 10);
        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 45000);

        if (this.cookie != null) {
            client.getCookieStore().addCookie(cookie);
        }

        try {
            HttpResponse response = null;

            if (request.getMethod() == RequestType.GET) {
                HttpGet method = new HttpGet(HTTPUtils.parseParameters(request));

                for (Header head : request.getHeaders()) {
                    method.setHeader(head);
                }

                response = client.execute(method);
            } else {
                HttpPost method = new HttpPost(request.getURL());

                for (Header head : request.getHeaders()) {
                    method.setHeader(head);
                }

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                for (String key : request.getParameters().keySet()) {
                    params.add(new BasicNameValuePair(key, request.getParameters().get(key)));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");

                method.setEntity(entity);
                response = client.execute(method);
            }

            Response resp = new WebResponse(request.getPlugin());
            resp.setContent(CharStreams.toString(new InputStreamReader(response.getEntity().getContent())));
            resp.setHTTPCode(new HTTPCode(response.getStatusLine().getStatusCode()));
            resp.setContentType(response.getEntity().getContentType().getValue());
            resp.setCookies(client.getCookieStore().getCookies());
            resp.setHeaders(Arrays.asList(response.getAllHeaders()));

            return resp;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new NetworkException("Failed to get URL " + request.getURL() + " (" + ex.getMessage() + ")");
        } finally {
            client.getConnectionManager().shutdown();
        }

    }
}
