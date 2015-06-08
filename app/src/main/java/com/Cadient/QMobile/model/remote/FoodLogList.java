package com.Cadient.QMobile.model.remote;

import com.Cadient.QMobile.model.BaseModel;
import com.Cadient.QMobile.model.Meal;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Alex on 27.10.2014.
 */
public class FoodLogList extends BaseModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("calendar_dates")
    private List<String> dateList;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Meal> meals;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("food_log_entries")
    private List<FoodLogEntries> foodLogEntryList;

    public List<String> getDateList() {
        return dateList;
    }

    public void setDateList(List<String> dateList) {
        this.dateList = dateList;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public List<FoodLogEntries> getFoodLogEntryList() {
        return foodLogEntryList;
    }

    public void setFoodLogEntryList(List<FoodLogEntries> foodLogEntryList) {
        this.foodLogEntryList = foodLogEntryList;
    }
}
