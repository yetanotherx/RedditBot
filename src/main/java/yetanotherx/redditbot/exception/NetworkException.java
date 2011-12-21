package yetanotherx.redditbot.exception;

/**
 * Called when there is an HTTP problem.
 * 
 * @author yetanotherx
 */
public class NetworkException extends RedditException {

    private static final long serialVersionUID = 1L;

    public NetworkException(String string) {
        super(string);
    }

    public NetworkException() {
    }
}
