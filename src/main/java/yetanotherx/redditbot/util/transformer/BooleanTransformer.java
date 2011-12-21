package yetanotherx.redditbot.util.transformer;

import org.apache.commons.collections.Transformer;

/**
 * Apache Commons collection transformer
 * Transforms objects to booleans.
 * 
 * @author yetanotherx
 */
public class BooleanTransformer implements Transformer {

    public Object transform(Object o) {
        if( o == null ) {
            return null;
        }
        
        return Boolean.parseBoolean(o.toString());
    }
    
}
