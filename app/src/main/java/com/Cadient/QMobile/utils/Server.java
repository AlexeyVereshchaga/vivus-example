package com.Cadient.QMobile.utils;

/**
 * Created by Alexey Vereshchaga on 28.11.14.
 */
public enum Server {
    LIVE("live"), DEV("dev");

    private String type;

    Server(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
