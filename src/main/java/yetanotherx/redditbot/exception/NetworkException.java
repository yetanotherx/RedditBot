package yetanotherx.redditbot.exception;

public class NetworkException extends Exception {
    private static final long serialVersionUID = 1L;

    public NetworkException(String string) {
        super(string);
    }

    public NetworkException() {
    }
    
}
