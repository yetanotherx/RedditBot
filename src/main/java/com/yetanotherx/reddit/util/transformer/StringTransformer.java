package com.yetanotherx.reddit.util.transformer;

import org.apache.commons.collections.Transformer;

/**
 * Apache Commons collection transformer
 * Transforms objects to strings.
 * 
 * @author yetanotherx
 */
public class StringTransformer implements Transformer {

    public Object transform(Object o) {
        if( o == null ) {
            return null;
        }
        
        return o.toString();
    }
    
}
