package com.Cadient.QMobile.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Alex on 16.11.2014.
 */
public class MealForLog extends BaseModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("is_favorite")
    private Boolean favorite;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Meal meal;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("meal_id")
    private Integer mealId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Float multiplier;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("slot_id")
    private Integer slotId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Unit unit;
    // crutch for the server removed @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("unit_id")
    private Long unitId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("unit_name")
    private String unitName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("yyyymmdd")
    private Integer date;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("multiplier_converted_for_entry_unit")
    private Float multiplierConvertedForEntryUnit;

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
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

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Float getMultiplierConvertedForEntryUnit() {
        return multiplierConvertedForEntryUnit;
    }

    public void setMultiplierConvertedForEntryUnit(Float multiplierConvertedForEntryUnit) {
        this.multiplierConvertedForEntryUnit = multiplierConvertedForEntryUnit;
    }
}
