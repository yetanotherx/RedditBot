package yetanotherx.redditbot;

import yetanotherx.redditbot.api.API;
import yetanotherx.redditbot.http.Transport;
import yetanotherx.redditbot.http.TransportType;
import yetanotherx.redditbot.lahwran.fevents.EventDispatcher;

public abstract class RedditPlugin {

    protected RedditBot controller;
    protected EventDispatcher dispatcher;
    protected Transport transport;
    protected API api;

    public RedditBot getController() {
        return controller;
    }

    public EventDispatcher getDispatcher() {
        return dispatcher;
    }

    public API getAPI() {
        return api;
    }

    public TransportType getTransportType() {
        return TransportType.JSOUP;
    }
    
    public Transport getTransport() {
        if( transport == null ) {
            transport = Transport.createTransport(this);
        }
        return transport;
    }
    
    public String getUserAgent() {
        return getName() + getVersion();
    }
    
    public abstract String getName();
    public abstract String getVersion();
    
}
