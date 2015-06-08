package com.Cadient.QMobile.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ivan on 11/7/14.
 */
public class Data extends BaseModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("registration_date")
    private String registrationDate;

    public String getRegistrationDate() {
        return registrationDate;
    }
}
