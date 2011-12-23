package com.yetanotherx.reddit.exception;

/**
 * Called when the JSON parser cannot parse the received result.
 * 
 * @author yetanotherx
 */
public class ParserException extends RedditException {

    private static final long serialVersionUID = 1L;
    
    public ParserException(String string) {
        super(string);
    }

    public ParserException() {
    }
    
}
