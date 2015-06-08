package com.Cadient.QMobile.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by alexey on 27.08.14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseModel {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("errors")
    private Map<String, String[]> error;

    public Map<String, String[]> getError() {
        return error;
    }

    public void setError(Map<String, String[]> error) {
        this.error = error;
    }
}
