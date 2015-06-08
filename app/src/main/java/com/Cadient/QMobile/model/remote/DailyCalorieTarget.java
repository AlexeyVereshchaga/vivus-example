package com.Cadient.QMobile.model.remote;


import com.Cadient.QMobile.model.BaseModel;
import com.Cadient.QMobile.model.Current;
import com.Cadient.QMobile.model.Goal;
import com.Cadient.QMobile.model.Today;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by alexey on 21.08.14.
 */
public class DailyCalorieTarget extends BaseModel {

    private Current current;
    private Goal goal;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Today today;

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

    public Today getToday() {
        return today;
    }

    public void setToday(Today today) {
        this.today = today;
    }
}
