package com.Cadient.QMobile.model.remote;

import com.Cadient.QMobile.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ivan on 9/2/14.
 */
public class WeightTrackerEntries extends BaseModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("weight_tracker_entries")
    private List<WeightTrackerEntry> weightTrackerEntries;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("profile_weight_in_pounds")
    private BigDecimal profileWeightInPounds;

    public List<WeightTrackerEntry> getWeightTrackerEntries() {
        return weightTrackerEntries;
    }

    public void setWeightTrackerEntries(List<WeightTrackerEntry> weightTrackerEntries) {
        this.weightTrackerEntries = weightTrackerEntries;
    }

    public BigDecimal getProfileWeightInPounds() {
        return profileWeightInPounds;
    }

    public void setProfileWeightInPounds(BigDecimal profileWeightInPounds) {
        this.profileWeightInPounds = profileWeightInPounds;
    }
}
