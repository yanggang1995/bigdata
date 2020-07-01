package com.reptile.proxy.yg.service.impl;

import java.util.HashMap;

/**
 * TODO
 *
 * @author Y.G
 * @date 2020/4/17 16:36
 **/
public class HashMapReader {

    public static void main(String[] args) {
        HashMap<String, String> learnMap = new HashMap<>(16);
        String key = "test";
        learnMap.put(key, "");
        learnMap.get(key);
        learnMap.remove(key);
        System.out.println(Integer.toBinaryString(key.hashCode()));
        System.out.println(Integer.toBinaryString(hash(key)));
    }


    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

}
