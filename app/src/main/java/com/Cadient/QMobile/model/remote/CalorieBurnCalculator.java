package com.Cadient.QMobile.model.remote;

import com.Cadient.QMobile.model.Activity;
import com.Cadient.QMobile.model.BaseModel;
import com.Cadient.QMobile.model.Height;
import com.Cadient.QMobile.model.Weight;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by alexey on 21.08.14.
 */
public class CalorieBurnCalculator extends BaseModel {

    @JsonProperty("birth_date")
    private int birthDate;

    private Weight weight;
    private Height height;
    @JsonProperty("activity_id")
    private int activityId;
    private int duration;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("calories_burned")
    private Float caloriesBurned;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Activity activity;
    private char gender;

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public Height getHeight() {
        return height;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Float getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(Float caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
