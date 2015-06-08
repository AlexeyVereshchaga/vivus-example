package com.Cadient.QMobile.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by alexey on 21.08.14.
 */
public class Activity extends BaseModel {

    private int id;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String source;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
