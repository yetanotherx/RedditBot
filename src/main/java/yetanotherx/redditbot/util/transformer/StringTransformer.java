package yetanotherx.redditbot.util.transformer;

import org.apache.commons.collections.Transformer;

public class StringTransformer implements Transformer {

    public Object transform(Object o) {
        if( o == null ) {
            return null;
        }
        
        return o.toString();
    }
    
}
