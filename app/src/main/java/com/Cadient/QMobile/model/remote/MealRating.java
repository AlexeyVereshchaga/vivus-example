package com.Cadient.QMobile.model.remote;

import com.Cadient.QMobile.database.DataBaseUtils;
import com.Cadient.QMobile.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by ivan on 8/22/14.
 */

@DatabaseTable(tableName = DataBaseUtils.MEAL_RATING_TABLE)
public class MealRating extends BaseModel {

//    @DatabaseField(columnName = DataBaseUtils.ID, id = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

//    @DatabaseField(columnName = DataBaseUtils.MEAL_ID)
    @JsonProperty("meal_id")
    private Integer mealId;

//    @DatabaseField
    private int value;

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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
