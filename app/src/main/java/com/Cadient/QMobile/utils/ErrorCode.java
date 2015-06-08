package com.Cadient.QMobile.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexey Vereshchaga on 03.10.14.
 */
public enum ErrorCode {
    NETWORK_NOT_AVAILABLE(-1, "Network is not available"),
    REQUEST_CANCELLED(-2, "Request has been cancelled explicitely."),
    CODE_200(200, "Wrong id"),
    UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"),
    NOT_FOUND(404, "Not found"),
    UNAUTHORIZED(401, "Email or password is incorrect");

    private Integer code;
    private String message;
    private static Map<String, Integer> mapCodeByMessage;


    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static Integer getCodeByMessage(String message) {
        if (mapCodeByMessage == null) {
            initializeMappingNameById();
        }
        if (mapCodeByMessage.containsKey(message)) {
            return mapCodeByMessage.get(message);
        }
        return null;
    }

    private static void initializeMappingNameById() {
        mapCodeByMessage = new HashMap<String, Integer>();
        for (ErrorCode errorCode : ErrorCode.values()) {
            mapCodeByMessage.put(errorCode.message, errorCode.code);
        }
    }
}
