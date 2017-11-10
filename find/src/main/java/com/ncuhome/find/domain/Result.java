package com.ncuhome.find.domain;

import java.util.HashMap;
import java.util.Map;

public class Result {
    private Integer code;
    private String message;
    Map mapResult;

    public Result(Integer code, String message) {
        mapResult = new HashMap();
        mapResult.put("code",code);
        mapResult.put("message",message);
    }

    public Result(Integer code, String message,HashMap map) {
        mapResult = new HashMap();
        mapResult.put("code",code);
        mapResult.put("message",message);
        mapResult.putAll(map);
    }

    public Map getMapResult(){
        return mapResult;
    }

}
