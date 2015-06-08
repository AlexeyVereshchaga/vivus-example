package com.Cadient.QMobile.model.remote;

import com.Cadient.QMobile.model.BaseModel;
import com.Cadient.QMobile.model.DailyCalorie;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Alexey Vereshchaga on 13.10.14.
 */
public class ActivitiesList extends BaseModel {

    @JsonProperty("dates")
    private List<String> dateList;
    @JsonProperty("calories_bank")
    private DailyCalorie dailyCalorie;
    @JsonProperty("third_party_identities")
    private List<String> thirdPartyIdentities;
    @JsonProperty("entries")
    private List<ActivityLogEntries> activityLogEntriesList;

    public List<ActivityLogEntries> getActivityLogEntriesList() {
        return activityLogEntriesList;
    }

    public void setActivityLogEntriesList(List<ActivityLogEntries> activityLogEntriesList) {
        this.activityLogEntriesList = activityLogEntriesList;
    }

    public List<String> getDateList() {
        return dateList;
    }

    public void setDateList(List<String> dateList) {
        this.dateList = dateList;
    }

    public DailyCalorie getDailyCalorie() {
        return dailyCalorie;
    }

    public void setDailyCalorie(DailyCalorie dailyCalorie) {
        this.dailyCalorie = dailyCalorie;
    }

    public List<String> getThirdPartyIdentities() {
        return thirdPartyIdentities;
    }

    public void setThirdPartyIdentities(List<String> thirdPartyIdentities) {
        this.thirdPartyIdentities = thirdPartyIdentities;
    }

}
