package com.Cadient.QMobile.model.remote;

import com.Cadient.QMobile.model.BaseModel;
import com.Cadient.QMobile.model.Meal;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Alex on 16.11.2014.
 */
public class FoodLogCreatedEntry extends BaseModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("food_log_entries")
    private FoodLogEntries[] foodLogEntries;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Meal[] meals;

    public Meal[] getMeals() {
        return meals;
    }

    public void setMeals(Meal[] meals) {
        this.meals = meals;
    }

    public FoodLogEntries[] getFoodLogEntries() {
        return foodLogEntries;
    }

    public void setFoodLogEntries(FoodLogEntries[] foodLogEntries) {
        this.foodLogEntries = foodLogEntries;
    }
}
