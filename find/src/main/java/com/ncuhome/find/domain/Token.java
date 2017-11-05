package com.ncuhome.find.domain;

public class Token {
    public void setKey(String key) {
        this.key = key;
    }

    private String key = "token";

    private String value;

    public Token(String token) {
        value = token;
    }

    public String getValue() {
        return value;
    }
}
