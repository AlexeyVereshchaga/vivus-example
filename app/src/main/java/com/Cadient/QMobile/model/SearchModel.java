package com.Cadient.QMobile.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Alexey Vereshchaga on 03.09.14.
 */
public class SearchModel {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("page_size")
    private Integer pageSize;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("page_number")
    private Integer pageNumber;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("order_by")
    private String orderBy;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("order_direction")
    private String orderDirection;

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
