package com.Cadient.QMobile.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ivan on 9/4/14.
 */
public class Recipe extends BaseModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int id;
    private float multiplier;
    @JsonProperty("wrapper_meal_id")
    private int wrapperMealID;
    @JsonProperty("unit_id")
    private int unitID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(float multiplier) {
        this.multiplier = multiplier;
    }

    public int getWrapperMealID() {
        return wrapperMealID;
    }

    public void setWrapperMealID(int wrapperMealID) {
        this.wrapperMealID = wrapperMealID;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }
}
