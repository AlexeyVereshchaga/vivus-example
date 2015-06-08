package com.Cadient.QMobile.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Alexey Vereshchaga on 01.09.14.
 */
public class FoodNutrient extends BaseModel {
    @JsonProperty("nutrient_id")
    private Long nutrientId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Float amount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("amount_raw")
    private Float amountRaw;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("amount_for_display")
    private String amountForDisplay;

    public String getAmountForDisplay() {
        return amountForDisplay;
    }

    public void setAmountForDisplay(String amountForDisplay) {
        this.amountForDisplay = amountForDisplay;
    }

    public Float getAmountRaw() {
        return amountRaw;
    }

    public void setAmountRaw(Float amountRaw) {
        this.amountRaw = amountRaw;
    }

    public Long getNutrientId() {
        return nutrientId;
    }

    public void setNutrientId(Long nutrientId) {
        this.nutrientId = nutrientId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
