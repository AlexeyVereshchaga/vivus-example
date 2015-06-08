package com.Cadient.QMobile.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Alexey Vereshchaga on 04.09.14.
 */
public class Item extends BaseModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String slot;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Float amount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("amount_parts")
    private String[] amountParts;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String unit;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("unit_parts")
    private String[] unitParts;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String food;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("food_parts")
    private String[] foodParts;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String[] discarded;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Meal meal;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("meal_url")
    private String mealUrl;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("meal_name")
    private String mealName;

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String[] getAmountParts() {
        return amountParts;
    }

    public void setAmountParts(String[] amountParts) {
        this.amountParts = amountParts;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String[] getUnitParts() {
        return unitParts;
    }

    public void setUnitParts(String[] unitParts) {
        this.unitParts = unitParts;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String[] getFoodParts() {
        return foodParts;
    }

    public void setFoodParts(String[] foodParts) {
        this.foodParts = foodParts;
    }

    public String[] getDiscarded() {
        return discarded;
    }

    public void setDiscarded(String[] discarded) {
        this.discarded = discarded;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public String getMealUrl() {
        return mealUrl;
    }

    public void setMealUrl(String mealUrl) {
        this.mealUrl = mealUrl;
    }
}
