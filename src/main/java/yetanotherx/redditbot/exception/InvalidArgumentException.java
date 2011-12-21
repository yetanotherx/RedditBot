package yetanotherx.redditbot.exception;

/**
 * Called when an argument cannot be used in a method.
 * 
 * @author yetanotherx
 */
public class InvalidArgumentException extends RedditException {

    private static final long serialVersionUID = 1L;
    
    public InvalidArgumentException(String string) {
        super(string);
    }

    public InvalidArgumentException() {
    }
    
}
