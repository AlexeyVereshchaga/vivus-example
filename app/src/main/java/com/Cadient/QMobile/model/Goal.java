package com.Cadient.QMobile.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by alexey on 21.08.14.
 */
public class Goal extends BaseModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Weight weight;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("start_date")
    private Integer startDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("end_date")
    private Integer endDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Start start;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private End end;

    public Start getStart() {
        return start;
    }

    public void setStart(Start start) {
        this.start = start;
    }

    public End getEnd() {
        return end;
    }

    public void setEnd(End end) {
        this.end = end;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public Integer getStartDate() {
        return startDate;
    }

    public void setStartDate(Integer startDate) {
        this.startDate = startDate;
    }

    public Integer getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

}
