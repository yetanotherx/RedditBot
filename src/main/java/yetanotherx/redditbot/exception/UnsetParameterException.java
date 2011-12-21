package yetanotherx.redditbot.exception;

public class UnsetParameterException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public UnsetParameterException(String string) {
        super(string);
    }

    public UnsetParameterException() {
    }
    
}
