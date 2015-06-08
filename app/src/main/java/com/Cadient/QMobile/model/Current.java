package com.Cadient.QMobile.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by alexey on 21.08.14.
 */
public class Current extends BaseModel {

    private Weight weight;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Height height;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Character gender;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("birth_date")
    private Integer birthDate; //DATE, FORMAT: "YYYYMMDD" ex. "birth_date":19821112

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

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Integer getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Integer birthDate) {
        this.birthDate = birthDate;
    }
}
