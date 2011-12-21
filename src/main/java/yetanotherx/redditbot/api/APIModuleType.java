package yetanotherx.redditbot.api;

import yetanotherx.redditbot.api.module.Save;
import yetanotherx.redditbot.api.module.Flair;
import yetanotherx.redditbot.api.module.CurrentUser;
import yetanotherx.redditbot.api.module.Unhide;
import yetanotherx.redditbot.api.module.Hide;
import yetanotherx.redditbot.api.module.Submit;
import yetanotherx.redditbot.api.module.Vote;
import yetanotherx.redditbot.api.module.Comment;
import yetanotherx.redditbot.api.module.Domain;
import yetanotherx.redditbot.api.module.Login;
import yetanotherx.redditbot.api.module.Unsave;
import yetanotherx.redditbot.api.module.FlairList;
import yetanotherx.redditbot.api.module.Subscriptions;
import yetanotherx.redditbot.api.module.AboutURL;
import yetanotherx.redditbot.RedditPlugin;

public enum APIModuleType {
    
    ABOUTURL(AboutURL.class),
    COMMENT(Comment.class),
    CURRENTUSER(CurrentUser.class),
    DOMAIN(Domain.class),
    FLAIR(Flair.class),
    FLAIRLIST(FlairList.class),
    HIDE(Hide.class),
    LOGIN(Login.class),
    SAVE(Save.class),
    SUBMIT(Submit.class),
    SUBSCRIPTIONS(Subscriptions.class),
    UNHIDE(Unhide.class),
    UNSAVE(Unsave.class),
    VOTE(Vote.class);
    
    protected Class<? extends APIModule> clazz;

    private APIModuleType(Class<? extends APIModule> clazz) {
        this.clazz = clazz;
    }
    
    public APIModule newInstance(RedditPlugin plugin) {
        try {
            return (APIModule) clazz.getConstructor(RedditPlugin.class).newInstance(plugin);
        } catch (Exception ex) {
            return null;
        }
    }
    
}
