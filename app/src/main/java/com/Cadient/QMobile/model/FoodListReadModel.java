package com.Cadient.QMobile.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexey Vereshchaga on 03.09.14.
 */
public class FoodListReadModel {

    private Integer pageNumber;
    private Integer pageSize;
    private String orderBy;
    private String orderDirection;
    private Boolean withDeleted;
    private Boolean paginated;

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    public void setWithDeleted(Boolean withDeleted) {
        this.withDeleted = withDeleted;
    }

    public void setPaginated(Boolean paginated) {
        this.paginated = paginated;
    }

    public Map<String, Object> getOptionsMap() {
        return new HashMap<String, Object>() {
            {
                put("page_number", pageNumber);
                put("page_size", pageSize);
                put("order_by", orderBy);
                put("order_direction", orderDirection);
                put("with_deleted", withDeleted);
                put("paginated", paginated);
            }
        };
    }
}
