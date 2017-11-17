package com.ncuhome.find.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateToLongUtil {
    public static Long convert(String date) throws NumberFormatException, ParseException {
        Long longDate;
        String dateString;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (isNumber(date)) {
            if (date.length() == 13) {
                longDate = Long.valueOf(date);
                return CompareWithNowTime(longDate);
            } else if (date.length() == 4) {
                dateString = date + "-01-01 00:00:00";
            } else if (date.length() == 6) {
                dateString = date.substring(0, 4) + "-" + date.substring(4) + "-01 00:00:00";
            } else if (date.length() == 8) {
                dateString = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6) + " 00:00:00";
            } else {
                throw new ParseException("", 0);
            }
            longDate = format.parse(dateString).getTime();
        } else if (date.contains("-")) {
            String string = date.replace("-", "");
            longDate = convert(string);
        } else {
            throw new ParseException("", 0);
        }
        return CompareWithNowTime(longDate);
    }

    private static boolean isNumber(String string) {
        try {
            for (int i = 0; i < string.length(); i++) {
                Integer.valueOf(string.substring(i, i + 1));
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static Long CompareWithNowTime(Long date) throws ParseException {
        if (date.compareTo(System.currentTimeMillis()) > 0) {
            throw new ParseException("", 0);
        } else {
            return date;
        }
    }
}
