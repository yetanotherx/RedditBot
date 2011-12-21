package yetanotherx.redditbot.util.transformer;

import org.apache.commons.collections.Transformer;

public class DoubleTransformer implements Transformer {

    public Object transform(Object o) {
        if( o == null ) {
            return null;
        }
        
        return Double.parseDouble(o.toString());
    }
    
}
