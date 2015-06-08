package com.Cadient.QMobile.model.remote;

import com.Cadient.QMobile.model.BaseModel;
import com.Cadient.QMobile.model.Meal;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Alexey Vereshchaga on 04.09.14.
 */
public class MealTranslator extends BaseModel {

    @JsonProperty("external_recipe_id")
    private String externalRecipeId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Meal meal;

    public String getExternalRecipeId() {
        return externalRecipeId;
    }

    public void setExternalRecipeId(String externalRecipeId) {
        this.externalRecipeId = externalRecipeId;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
}
