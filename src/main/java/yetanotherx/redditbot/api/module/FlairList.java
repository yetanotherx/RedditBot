package yetanotherx.redditbot.api.module;

import yetanotherx.redditbot.RedditPlugin;
import yetanotherx.redditbot.api.APIModule;
import yetanotherx.redditbot.exception.RedditException;

public class FlairList extends APIModule {

    public FlairList(RedditPlugin plugin) {
        super(plugin);
    }

    @Override
    public void execute() throws RedditException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
