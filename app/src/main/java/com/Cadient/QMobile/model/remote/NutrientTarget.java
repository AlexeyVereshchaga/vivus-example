package com.Cadient.QMobile.model.remote;

import com.Cadient.QMobile.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Alexey Vereshchaga on 17.11.14.
 */
public class NutrientTarget extends BaseModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Float min;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Float max;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("unit_name_for_display")
    private String unitNameForDisplay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getMin() {
        return min;
    }

    public void setMin(Float min) {
        this.min = min;
    }

    public Float getMax() {
        return max;
    }

    public void setMax(Float max) {
        this.max = max;
    }

    public String getUnitNameForDisplay() {
        return unitNameForDisplay;
    }

    public void setUnitNameForDisplay(String unitNameForDisplay) {
        this.unitNameForDisplay = unitNameForDisplay;
    }
}
