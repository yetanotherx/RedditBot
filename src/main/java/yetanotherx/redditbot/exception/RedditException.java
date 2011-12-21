package yetanotherx.redditbot.exception;

/**
 * Checking exceptions is annoying. All RedditBot exceptions
 * extend this, and it's RuntimeException so it's not checked.
 * 
 * @author yetanotherx
 */
public class RedditException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public RedditException(String string) {
        super(string);
    }

    public RedditException() {
    }
    
}
