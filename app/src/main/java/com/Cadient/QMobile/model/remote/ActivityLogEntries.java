package com.Cadient.QMobile.model.remote;

import com.Cadient.QMobile.model.Activity;
import com.Cadient.QMobile.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;

/**
 * Created by ivan on 9/2/14.
 */
public class ActivityLogEntries extends BaseModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("yyyymmdd")
    private Integer date;
    @JsonProperty("activity_id")
    private Integer activityId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Activity activity;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigInteger duration;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigInteger miles;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigInteger steps;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("heart_rate")
    private BigInteger heartRate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigInteger calories;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("external_id")
    private String externalId;

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

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public BigInteger getDuration() {
        return duration;
    }

    public void setDuration(BigInteger duration) {
        this.duration = duration;
    }

    public BigInteger getMiles() {
        return miles;
    }

    public void setMiles(BigInteger miles) {
        this.miles = miles;
    }

    public BigInteger getSteps() {
        return steps;
    }

    public void setSteps(BigInteger steps) {
        this.steps = steps;
    }

    public BigInteger getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(BigInteger heartRate) {
        this.heartRate = heartRate;
    }

    public BigInteger getCalories() {
        return calories;
    }

    public void setCalories(BigInteger calories) {
        this.calories = calories;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}
