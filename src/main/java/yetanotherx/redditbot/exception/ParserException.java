package yetanotherx.redditbot.exception;

public class ParserException extends RedditException {

    private static final long serialVersionUID = 1L;
    
    public ParserException(String string) {
        super(string);
    }

    public ParserException() {
    }
    
}
