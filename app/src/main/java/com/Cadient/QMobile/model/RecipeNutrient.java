package com.Cadient.QMobile.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Alexey Vereshchaga on 03.09.14.
 */
public class RecipeNutrient extends BaseModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Float amount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("amount_for_display")
    private String amountForDisplay;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("unit_name_for_display")
    private String unitNameForDisplay;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("needs_attention")
    private Boolean needsAttention;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Nutrient nutrient;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("nutrient_id")
    private Integer nutrientId;

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Nutrient getNutrient() {
        return nutrient;
    }

    public void setNutrient(Nutrient nutrient) {
        this.nutrient = nutrient;
    }

    public String getAmountForDisplay() {
        return amountForDisplay;
    }

    public void setAmountForDisplay(String amountForDisplay) {
        this.amountForDisplay = amountForDisplay;
    }

    public Boolean getNeedsAttention() {
        return needsAttention;
    }

    public void setNeedsAttention(Boolean needsAttention) {
        this.needsAttention = needsAttention;
    }

    public String getUnitNameForDisplay() {
        return unitNameForDisplay;
    }

    public void setUnitNameForDisplay(String unitNameForDisplay) {
        this.unitNameForDisplay = unitNameForDisplay;
    }

    public Integer getNutrientId() {
        return nutrientId;
    }

    public void setNutrientId(Integer nutrientId) {
        this.nutrientId = nutrientId;
    }
}
