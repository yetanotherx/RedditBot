package com.yetanotherx.reddit.api;

/**
 * List of known Reddit error messages.
 * 
 * @author yetanotherx
 */
public enum APIError {

    USER_REQUIRED("User is not logged in."),
    VERIFIED_USER_REQUIRED("User does not have a validated email."),
    NO_URL("No URL was provided."),
    BAD_URL("URL is invalid/broken."),
    BAD_CAPTCHA("CAPTCHA was invalid."),
    BAD_USERNAME("Username was invalid"),
    USERNAME_TAKEN("Username is already taken"),
    USERNAME_TAKEN_DEL("Username is already taken, but the account was deleted."),
    USER_BLOCKED("Cannot send to a user that you have blocked"),
    NO_THING_ID("ID was not specified"),
    NOT_AUTHOR("Only the author may perform that action."),
    NOT_USER("Not logged in as that user"),
    DELETED_LINK("the link you are commenting on has been deleted"),
    DELETED_COMMENT("that comment has been deleted"),
    DELETED_THING("that element has been deleted"),
    BAD_PASSWORD("that password is unacceptable"),
    WRONG_PASSWORD("invalid password"),
    BAD_PASSWORD_MATCH("passwords do not match"),
    NO_NAME("please enter a name"),
    NO_EMAIL("please enter an email address"),
    NO_EMAIL_FOR_USER("no email address for that user"),
    NO_TO_ADDRESS("send it to whom?"),
    NO_SUBJECT("please enter a subject"),
    USER_DOESNT_EXIST("that user doesn't exist"),
    NO_USER("please enter a username"),
    BAD_NUMBER("that number isn't in the right range (%(min)d to %(max)d)"),
    BAD_STRING("you used a character here that we can't handle"),
    BAD_BID("your bid must be at least $%(min)d per day and no more than to $%(max)d in total."),
    ALREADY_SUB("that link has already been submitted"),
    SUBREDDIT_EXISTS("that reddit already exists"),
    SUBREDDIT_NOEXIST("that reddit doesn\'t exist"),
    SUBREDDIT_NOTALLOWED("you aren't allowed to post there."),
    SUBREDDIT_REQUIRED("you must specify a subreddit"),
    BAD_SR_NAME("that name isn\'t going to work"),
    RATELIMIT("you are doing that too much. try again in %(time)s."),
    EXPIRED("your session has expired"),
    DRACONIAN("you must accept the terms first"),
    INVALID_OPTION("that option is not valid"),
    BAD_EMAILS("the following emails are invalid: %(emails)s"),
    NO_EMAILS("please enter at least one email address"),
    TOO_MANY_EMAILS("please only share to %(num)s emails at a time."),
    OVERSOLD("that reddit has already been oversold on %(start)s to %(end)s. Please pick another reddit or date."),
    BAD_DATE("please provide a date of the form mm/dd/yyyy"),
    BAD_DATE_RANGE("the dates need to be in order and not identical"),
    BAD_FUTURE_DATE("please enter a date at least %(day)s days in the future"),
    BAD_PAST_DATE("please enter a date at least %(day)s days in the past"),
    BAD_ADDRESS("address problem: %(message)s"),
    BAD_CARD("card problem: %(message)s"),
    TOO_LONG("this is too long (max: %(max_length)s)"),
    NO_TEXT("we need something here"),
    INVALID_CODE("we've never seen that code before"),
    CLAIMED_CODE("that code has already been claimed -- perhaps by you?"),
    NO_SELFS("that reddit doesn't allow text posts"),
    NO_LINKS("that reddit only allows text posts"),
    TOO_OLD("that's a piece of history now; it's too late to reply to it"),
    BAD_CSS_NAME("invalid css name"),
    TOO_MUCH_FLAIR_CSS("too many flair css classes"),
    OAUTH2_INVALID_CLIENT("invalid client id"),
    OAUTH2_ACCESS_DENIED("access denied by the user"),
    CONFIRM("please confirm the form"),
    GENERIC("Unknown error");
    
    protected String message;

    private APIError(String message) {
        this.message = message;
    }

    /**
     * Returns a more verbose error message
     * 
     * @return 
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * Gets the error with the given key. 
     * If it's not found, return GENERIC rather than null.
     * 
     * @param key
     * @return 
     */
    public static APIError realValueOf(String key) {
        APIError error = APIError.valueOf(key);
        if( error == null ) {
            return GENERIC;
        }
        return error;
    }
}
