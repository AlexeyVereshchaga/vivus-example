package com.Cadient.QMobile.model.remote;

import com.Cadient.QMobile.model.Content;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by ivan on 9/4/14.
 */
public class FoodLogNutrients {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("yyyymmdd")
    private String date;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "slots")
    private Map<Integer, Content> slots;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<Integer, Content> getSlots() {
        return slots;
    }

    public void setSlots(Map<Integer, Content> slots) {
        this.slots = slots;
    }
}
