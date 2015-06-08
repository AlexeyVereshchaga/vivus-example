package com.Cadient.QMobile.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Alexey Vereshchaga on 03.09.14.
 */
public class SearchByCategory extends SearchModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("category_ids")
    private Integer[] categoryId;

    public Integer[] getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer[] categoryId) {
        this.categoryId = categoryId;
    }
}
