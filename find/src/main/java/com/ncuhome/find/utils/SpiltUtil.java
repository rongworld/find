package com.ncuhome.find.utils;

import java.util.StringTokenizer;

public class SpiltUtil {
    public static String[] splitString(String string, String delim) {
        StringTokenizer stringTokenizer = new StringTokenizer(string, delim);
        int count = stringTokenizer.countTokens();
        String[] lostArray = new String[count];
        while (--count != -1) {
            lostArray[count] = stringTokenizer.nextToken();
        }
        return lostArray;
    }
}
