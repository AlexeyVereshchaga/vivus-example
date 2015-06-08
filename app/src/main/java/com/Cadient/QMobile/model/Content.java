package com.Cadient.QMobile.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;


/**
 * Created by ivan on 9/4/14.
 */

public class Content {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Slot slot;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "nutrients")
    private Map<Integer, Nutrients> nutrients;

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Map<Integer, Nutrients> getNutrients() {
        return nutrients;
    }

    public void setNutrients(Map<Integer, Nutrients> nutrients) {
        this.nutrients = nutrients;
    }
}
