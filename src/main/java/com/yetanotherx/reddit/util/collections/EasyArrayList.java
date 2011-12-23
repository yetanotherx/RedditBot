package com.yetanotherx.reddit.util.collections;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Simple ArrayList utility. Initialize the arraylist
 * with a varargs set of variables. 
 * 
 * new EasyArrayList<String>("foo", "bar", "baz")
 * 
 * @author yetanotherx
 */
public class EasyArrayList<T> extends ArrayList<T> {
    private static final long serialVersionUID = 1L;

    public EasyArrayList(T ... values) {
        this.addAll(Arrays.asList(values));
    }
}
