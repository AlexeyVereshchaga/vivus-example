package com.Cadient.QMobile.model.remote;

import com.Cadient.QMobile.model.BaseModel;
import com.Cadient.QMobile.model.Meal;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Alexey Vereshchaga on 04.09.14.
 */
public class SuggestedMeal extends BaseModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String term;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("page_number")
    private Integer pageNumber;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("page_size")
    private Integer pageSize;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer found;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("hits")
    private List<Meal> meals;

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getFound() {
        return found;
    }

    public void setFound(Integer found) {
        this.found = found;
    }
}
