package yetanotherx.redditbot.exception;

public class APIException extends RedditException {
    private static final long serialVersionUID = 1L;

    public APIException(String string) {
        super(string);
    }

    public APIException() {
    }
    
}
