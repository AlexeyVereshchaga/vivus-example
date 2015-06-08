package com.Cadient.QMobile.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ivan on 8/25/14.
 */
public class Users extends BaseModel {
    @JsonProperty("id")
    private String nameUserId;
    private Weight weight;

    public String getNameUserId() {
        return nameUserId;
    }

    public void setNameUserId(String nameUserId) {
        this.nameUserId = nameUserId;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }
}
