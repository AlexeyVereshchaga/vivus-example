package com.Cadient.QMobile.model.remote;

import com.Cadient.QMobile.model.BaseModel;
import com.Cadient.QMobile.model.Users;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ivan on 8/25/14.
 */
public class UserWeight extends BaseModel {
    @JsonProperty("page_number")
    private int pageNumber;
    @JsonProperty("page_size")
    private int pageSize;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Users[] users;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int page_number) {
        this.pageNumber = page_number;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int page_size) {
        this.pageSize = page_size;
    }

    public Users[] getUsers() {
        return users;
    }

    public void setUsers(Users[] users) {
        this.users = users;
    }
}
