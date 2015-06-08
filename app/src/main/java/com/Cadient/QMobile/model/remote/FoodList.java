package com.Cadient.QMobile.model.remote;

import com.Cadient.QMobile.model.Food;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Alexey Vereshchaga on 02.09.14.
 */
public class FoodList {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("page_number")
    private Integer pageNumber;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("page_size")
    private Integer pageSize;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("order_by")
    private String order;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("order_direction")
    private String orderDirection;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("with_deleted")
    private Boolean withDeleted;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean paginated;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("total_records")
    private Integer totalRecords;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("foods")
    private List<Food> foodList;

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

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    public Boolean getWithDeleted() {
        return withDeleted;
    }

    public void setWithDeleted(Boolean withDeleted) {
        this.withDeleted = withDeleted;
    }

    public Boolean getPaginated() {
        return paginated;
    }

    public void setPaginated(Boolean paginated) {
        this.paginated = paginated;
    }

    public Integer getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }
}
