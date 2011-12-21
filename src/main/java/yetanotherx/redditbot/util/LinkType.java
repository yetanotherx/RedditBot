package yetanotherx.redditbot.util;

/**
 * Enum for the two types of links. Just makes life 
 * easier (or harder, depending on yoru view).
 * 
 * @author yetanotherx
 */
public enum LinkType {

    SELF,
    LINK;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
