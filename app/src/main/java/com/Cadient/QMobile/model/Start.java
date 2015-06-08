package com.Cadient.QMobile.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by ivan on 8/25/14.
 */
public class Start extends BaseModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Weight weight;
    private int date;

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
