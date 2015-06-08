package com.Cadient.QMobile.model.remote;

import com.Cadient.QMobile.model.BaseModel;
import com.Cadient.QMobile.model.Current;
import com.Cadient.QMobile.model.Goal;
import com.Cadient.QMobile.model.Name;
import com.Cadient.QMobile.model.Today;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ivan on 8/21/14.
 */
public class UserProfile extends BaseModel {

    private Name name;
    private Current current;
    private Goal goal;
    @JsonProperty("recommended_bucket_id")
    private long recommendedBucketId;
    @JsonProperty("override_bucket_id")
    private String overrideBucketId;
    private String email;
    @JsonProperty("integrate_plan_and_log")
    private boolean integratePlanAndLog;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Today today;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
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

    public long getRecommendedBucketId() {
        return recommendedBucketId;
    }

    public void setRecommendedBucketId(long recommendedBucketId) {
        this.recommendedBucketId = recommendedBucketId;
    }

    public String getOverrideBucketId() {
        return overrideBucketId;
    }

    public void setOverrideBucketId(String overrideBucketId) {
        this.overrideBucketId = overrideBucketId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIntegrate_plan_and_log() {
        return integratePlanAndLog;
    }

    public void setIntegrate_plan_and_log(boolean integrate_plan_and_log) {
        this.integratePlanAndLog = integrate_plan_and_log;
    }

    @JsonIgnore
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public Today getToday() {
        return today;
    }

    public void setToday(Today today) {
        this.today = today;
    }
}
