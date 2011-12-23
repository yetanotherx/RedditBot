package com.yetanotherx.reddit.exception;

/**
 * Called when a required parameter is not set.
 * 
 * @author yetanotherx
 */
public class UnsetParameterException extends RedditException {

    private static final long serialVersionUID = 1L;
    
    public UnsetParameterException(String string) {
        super(string);
    }

    public UnsetParameterException() {
    }
    
}
