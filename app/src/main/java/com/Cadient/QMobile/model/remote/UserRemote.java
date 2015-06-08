package com.Cadient.QMobile.model.remote;

import com.Cadient.QMobile.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by alexey on 15.08.14.
 */
@DatabaseTable(tableName = "user_remote")
public class UserRemote extends BaseModel {

//    @DatabaseField(columnName = "id", id = true)
    @JsonProperty("id")
    private Integer internalUserId;

//    @DatabaseField
    private String email;

//    @DatabaseField
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String username;

//    @DatabaseField(columnName = "external_user_id", canBeNull = false)
    @JsonProperty("external_user_id")
    private String externalUserId;

//    @DatabaseField(columnName = "api_key", canBeNull = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("api_key")
    private String apiKey;

    public UserRemote() {
    }

    public UserRemote(Integer internalUserId) {
        this.internalUserId = internalUserId;
    }

    public Integer getInternalUserId() {
        return internalUserId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(String externalUserId) {
        this.externalUserId = externalUserId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
