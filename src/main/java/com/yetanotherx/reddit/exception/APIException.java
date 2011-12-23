package com.yetanotherx.reddit.exception;

/**
 * Called when the API throws an error.
 * 
 * @author yetanotherx
 */
public class APIException extends RedditException {

    private static final long serialVersionUID = 1L;

    public APIException(String string) {
        super(string);
    }

    public APIException() {
    }
}
