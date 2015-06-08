package com.Cadient.QMobile.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created by Alexey Vereshchaga on 04.09.14.
 */
public class DailyCalorie extends BaseModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("yyyymmdd")
    private Integer date;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("calories_burned")
    private BigDecimal caloriesBurned;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("calories_consumed")
    private BigDecimal caloriesConsumed;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("target_calories")
    private BigDecimal targetCalories;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("weight_in_pounds")
    private BigDecimal weightInPounds;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("calories_target")
    private BigDecimal caloriesTarget;

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public BigDecimal getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(BigDecimal caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public BigDecimal getCaloriesConsumed() {
        return caloriesConsumed;
    }

    public void setCaloriesConsumed(BigDecimal caloriesConsumed) {
        this.caloriesConsumed = caloriesConsumed;
    }

    public BigDecimal getTargetCalories() {
        return targetCalories;
    }

    public void setTargetCalories(BigDecimal targetCalories) {
        this.targetCalories = targetCalories;
    }

    public BigDecimal getWeightInPounds() {
        return weightInPounds;
    }

    public void setWeightInPounds(BigDecimal weightInPounds) {
        this.weightInPounds = weightInPounds;
    }

    public BigDecimal getCaloriesTarget() {
        return caloriesTarget;
    }

    public void setCaloriesTarget(BigDecimal caloriesTarget) {
        this.caloriesTarget = caloriesTarget;
    }
}
