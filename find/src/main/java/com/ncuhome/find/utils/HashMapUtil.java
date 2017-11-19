package com.ncuhome.find.utils;

import java.util.HashMap;

public class HashMapUtil {
    public static HashMap getMap(String key, Object object) {
        if (key == null || object == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(key, object);
        return hashMap;
    }

    public static HashMap getMap(String[] keys, Object[] objects) {
        if (keys == null || objects == null || keys.length != objects.length) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (int i = 0; i < keys.length; i++) {
            hashMap.put(keys[i], objects[i]);
        }
        return hashMap;
    }
}
