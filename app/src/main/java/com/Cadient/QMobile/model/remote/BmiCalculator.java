package com.Cadient.QMobile.model.remote;

import com.Cadient.QMobile.model.BaseModel;
import com.Cadient.QMobile.model.Height;
import com.Cadient.QMobile.model.Weight;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.j256.ormlite.field.DatabaseField;

/**
 * Created by alexey on 20.08.14.
 */
public class BmiCalculator extends BaseModel {

    @DatabaseField
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Float bmi;
    @DatabaseField
    private Weight weight;
    @DatabaseField
    private Height height;

    public Float getBmi() {
        return bmi;
    }

    public void setBmi(Float bmi) {
        this.bmi = bmi;
    }

    public Height getHeight() {
        return height;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }
}