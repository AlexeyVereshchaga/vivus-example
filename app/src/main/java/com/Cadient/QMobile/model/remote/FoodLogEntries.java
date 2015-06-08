package com.Cadient.QMobile.model.remote;

import com.Cadient.QMobile.model.BaseModel;
import com.Cadient.QMobile.model.Meal;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ivan on 9/2/14.
 */
public class FoodLogEntries extends BaseModel {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("yyyymmdd")
    private Integer date;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("slot_id")
    private Integer slotId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("meal_id")
    private Integer mealId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Float multiplier;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("planned_for_today")
    private Boolean plannedForToday;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("unit_id")
    private Integer unitId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("preset_meal_id")
    private Integer presetMealId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("food_plan_entry_id")
    private Integer foodPlanEntryId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Meal meal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public Integer getMealId() {
        return mealId;
    }

    public void setMealId(Integer mealId) {
        this.mealId = mealId;
    }

    public Float getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Float multiplier) {
        this.multiplier = multiplier;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer heart_rate) {
        this.unitId = heart_rate;
    }

    public Integer getPresetMealId() {
        return presetMealId;
    }

    public void setPresetMealId(Integer presetMealId) {
        this.presetMealId = presetMealId;
    }

    public Integer getFoodPlanEntryId() {
        return foodPlanEntryId;
    }

    public void setFoodPlanEntryId(Integer foodPlanEntryId) {
        this.foodPlanEntryId = foodPlanEntryId;
    }

    public Boolean isPlannedForToday() {
        return plannedForToday;
    }

    public void setPlannedForToday(Boolean plannedForToday) {
        this.plannedForToday = plannedForToday;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
}
