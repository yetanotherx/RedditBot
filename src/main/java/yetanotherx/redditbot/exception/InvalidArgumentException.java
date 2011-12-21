package yetanotherx.redditbot.exception;

public class InvalidArgumentException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public InvalidArgumentException(String string) {
        super(string);
    }

    public InvalidArgumentException() {
    }
    
}
