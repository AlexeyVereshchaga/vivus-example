package com.Cadient.QMobile.model.remote;

import com.Cadient.QMobile.model.BaseModel;
import com.Cadient.QMobile.model.Meal;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ivan on 8/25/14.
 */
public class FavoriteUserMeal extends BaseModel {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("meal_id")
    private Integer mealId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String comments;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Meal meal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMealId() {
        return mealId;
    }

    public void setMealId(Integer mealId) {
        this.mealId = mealId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
}
