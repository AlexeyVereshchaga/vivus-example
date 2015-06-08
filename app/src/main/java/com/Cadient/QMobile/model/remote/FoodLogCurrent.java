package com.Cadient.QMobile.model.remote;

import com.Cadient.QMobile.model.BaseModel;
import com.Cadient.QMobile.model.Nutrient;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Alexey Vereshchaga on 17.11.14.
 */
public class FoodLogCurrent extends BaseModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("food_log_entries")
    private List<FoodLogEntries> logEntryList;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("nutrient_targets")
    private List<NutrientTarget> nutrientTargets;

    public List<NutrientTarget> getNutrientTargets() {
        return nutrientTargets;
    }

    public void setNutrientTargets(List<NutrientTarget> nutrientTargets) {
        this.nutrientTargets = nutrientTargets;
    }

    public List<FoodLogEntries> getLogEntryList() {
        return logEntryList;
    }

    public void setLogEntryList(List<FoodLogEntries> logEntryList) {
        this.logEntryList = logEntryList;
    }
}
