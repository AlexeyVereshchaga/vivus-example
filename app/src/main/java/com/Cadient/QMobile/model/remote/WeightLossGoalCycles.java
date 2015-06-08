package com.Cadient.QMobile.model.remote;

import com.Cadient.QMobile.model.BaseModel;
import com.Cadient.QMobile.model.Current;
import com.Cadient.QMobile.model.Goal;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by ivan on 8/25/14.
 */
public class WeightLossGoalCycles extends BaseModel {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    private Current current;
    private Goal goal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }
}
