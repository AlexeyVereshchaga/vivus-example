package com.Cadient.QMobile.model.remote;


import com.Cadient.QMobile.model.BaseModel;
import com.Cadient.QMobile.model.DailyCalorie;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Alexey Vereshchaga on 04.09.14.
 */
public class DailyCalorieRemote extends BaseModel {
    @JsonProperty("days")
    private List<DailyCalorie> dailyCalorieList;

    public List<DailyCalorie> getDailyCalorieList() {
        return dailyCalorieList;
    }

    public void setDailyCalorieList(List<DailyCalorie> dailyCalorieList) {
        this.dailyCalorieList = dailyCalorieList;
    }
}
