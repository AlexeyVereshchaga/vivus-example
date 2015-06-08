package com.Cadient.QMobile.model.remote;

import com.Cadient.QMobile.model.BaseModel;
import com.Cadient.QMobile.model.Meal;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Alexey Vereshchaga on 03.09.14.
 */
public class RecipeRemote extends BaseModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("page_size")
    private Integer pageSize;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("page_number")
    private Integer pageNumber;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("total_records")
    private Integer totalRecords;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("total_pages")
    private Integer totalPages;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("order_by")
    private String orderBy;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("order_direction")
    private String orderDirection;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Meal> meals;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("category_ids")
    private Integer[] categoryIds;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer cuisine;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("food_type")
    private Integer foodType;

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Integer getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    private String term;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getFoodType() {
        return foodType;
    }

    public void setFoodType(Integer foodType) {
        this.foodType = foodType;
    }

    public Integer[] getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(Integer[] categoryIds) {
        this.categoryIds = categoryIds;
    }

    public Integer getCuisine() {
        return cuisine;
    }

    public void setCuisine(Integer cuisine) {
        this.cuisine = cuisine;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
