package com.Cadient.QMobile.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Alexey Vereshchaga on 03.09.14.
 */
public class SearchByKeyword extends SearchModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String term;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
