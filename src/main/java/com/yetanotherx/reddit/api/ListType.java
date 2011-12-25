package com.yetanotherx.reddit.api;

public enum ListType {
    
    HOT(""),
    NEW("/new"),
    CONTROVERSIAL("/controversial"),
    TOP("/top");
    
    protected String url;

    private ListType(String url) {
        this.url = url;
    }

    public String getURL() {
        return url;
    }
    
}
