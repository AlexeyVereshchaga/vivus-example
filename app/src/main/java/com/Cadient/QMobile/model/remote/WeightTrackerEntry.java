package com.Cadient.QMobile.model.remote;

import com.Cadient.QMobile.model.BaseModel;
import com.Cadient.QMobile.utils.HelpUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by ivan on 9/2/14.
 */
public class WeightTrackerEntry extends BaseModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private long id;
    @JsonProperty("yyyymmdd")
    private int date;
    @JsonProperty("goal_weight_in_pounds")
    private BigDecimal goalWeightInPounds;
    private BigDecimal bmi;

    @JsonProperty("weight_in_pounds")
    private BigDecimal weightInPounds;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getConvertDate() {
        return HelpUtils.parseIntToDate(date);
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public BigDecimal getGoalWeightInPounds() {
        return goalWeightInPounds;
    }

    public void setGoalWeightInPounds(BigDecimal goalWeightInPounds) {
        this.goalWeightInPounds = goalWeightInPounds;
    }

    public BigDecimal getWeightInPounds() {
        return weightInPounds;
    }

    public void setWeightInPounds(BigDecimal weightInPounds) {
        this.weightInPounds = weightInPounds;
    }

    public BigDecimal getBmi() {
        return bmi;
    }

    public void setBmi(BigDecimal bmi) {
        this.bmi = bmi;
    }
}
