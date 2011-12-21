package yetanotherx.redditbot.util.collections;

import java.util.HashMap;
import yetanotherx.redditbot.exception.InvalidArgumentException;

/**
 * Simple HashMap utility. Initialize with an even number
 * of arguments, and it fills the hashmap!
 * 
 * new EasyHashMap<String, Integer>("foo", 1, "bar", 2)
 * 
 * @author yetanotherx
 */
public class EasyHashMap<T, U> extends HashMap<T, U> {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unchecked")
    public EasyHashMap(Object... values) {
        if (values.length % 2 != 0) {
            throw new InvalidArgumentException("Value count must be even");
        }
        boolean first = true;
        T saved = null;
        for (Object value : values) {
            if (first) {
                first = false;
                saved = (T) value;
            } else {
                first = true;
                this.put(saved, (U) value);
                saved = null;
            }
        }
    }
}
