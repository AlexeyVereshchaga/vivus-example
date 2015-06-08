package com.Cadient.QMobile.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by alexey on 21.08.14.
 */
public class Today extends BaseModel {

    private Calories calories;
    @JsonProperty("yyyymmdd")
    private int date;

    public Calories getCalories() {
        return calories;
    }

    public void setCalories(Calories calories) {
        this.calories = calories;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
