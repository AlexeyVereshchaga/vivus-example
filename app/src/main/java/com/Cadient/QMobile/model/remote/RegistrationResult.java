package com.Cadient.QMobile.model.remote;

import com.Cadient.QMobile.model.BaseModel;
import com.Cadient.QMobile.model.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by ivan on 11/7/14.
 */
public class RegistrationResult extends BaseModel {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean success;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Data data;

    public boolean isSuccess() {
        return success;
    }

    public Data getData() {
        return data;
    }
}
