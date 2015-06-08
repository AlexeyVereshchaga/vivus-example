package com.Cadient.QMobile.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Alexey Vereshchaga on 03.09.14.
 */

public class Nutrients extends BaseModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Nutrient nutrient;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private float amount;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("amount_for_display")
    private String amountForDisplay;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("unit_name_for_display")
    private String unitNameForDisplay;


    public Nutrient getNutrient() {
        return nutrient;
    }

    public void setNutrient(Nutrient nutrient) {
        this.nutrient = nutrient;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getAmountForDisplay() {
        return amountForDisplay;
    }

    public void setAmountForDisplay(String amountForDisplay) {
        this.amountForDisplay = amountForDisplay;
    }

    public String getUnitNameForDisplay() {
        return unitNameForDisplay;
    }

    public void setUnitNameForDisplay(String unitNameForDisplay) {
        this.unitNameForDisplay = unitNameForDisplay;
    }
}
