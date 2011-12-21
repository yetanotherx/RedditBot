package yetanotherx.redditbot.util.collections;

import java.util.ArrayList;
import java.util.Arrays;

public class EasyArrayList<T> extends ArrayList<T> {
    private static final long serialVersionUID = 1L;

    public EasyArrayList(T ... values) {
        this.addAll(Arrays.asList(values));
    }
}
