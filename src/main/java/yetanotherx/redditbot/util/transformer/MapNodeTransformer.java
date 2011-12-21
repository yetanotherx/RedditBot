package yetanotherx.redditbot.util.transformer;

import java.util.Map;
import org.apache.commons.collections.Transformer;
import yetanotherx.redditbot.util.MapNode;

/**
 * Apache Commons collection transformer
 * Transforms objects to MapNodes.
 * 
 * @author yetanotherx
 */
public class MapNodeTransformer implements Transformer {

    @SuppressWarnings("unchecked")
    public Object transform(Object o) {
        if( o == null ) {
            return null;
        }
        
        if( o instanceof Map ) {
            return new MapNode((Map<String, Object>) o);
        }
        else {
            return null;
        }
    }
    
}
