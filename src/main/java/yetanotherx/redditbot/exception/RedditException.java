package yetanotherx.redditbot.exception;

public class RedditException extends Exception {

    private static final long serialVersionUID = 1L;
    
    public RedditException(String string) {
        super(string);
    }

    public RedditException() {
    }
    
}
