package com.yetanotherx.reddit.util;

/**
 * Enum for upvotes/downvotes. Upvote has a value of 1,
 * downvote has a value of -1, and no vote has a value
 * of 0. You can also initialize them with a Boolean. 
 * True is up, false is down, and null is no vote.
 * 
 * @author yetanotherx
 */
public enum VoteType {

    UPVOTE(1),
    NOVOTE(0),
    DOWNVOTE(-1);
    protected Integer value;

    private VoteType(int value) {
        this.value = value;
    }

    public String toString() {
        return value.toString();
    }

    public static VoteType newFromBoolean(Boolean value) {
        if (value == null) {
            return NOVOTE;
        }
        else if (value) {
            return UPVOTE;

        } else {
            return DOWNVOTE;
        }
    }
}
