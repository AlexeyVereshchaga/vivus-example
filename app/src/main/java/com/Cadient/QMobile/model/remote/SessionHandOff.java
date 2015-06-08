package com.Cadient.QMobile.model.remote;

import com.Cadient.QMobile.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by alexey on 20.08.14.
 */
public class SessionHandOff extends BaseModel {

    @JsonProperty("user_id")
    private Integer userId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String guid;

    public SessionHandOff() {
    }

    public SessionHandOff(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

}
