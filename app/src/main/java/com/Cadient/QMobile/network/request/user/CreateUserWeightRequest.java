package com.Cadient.QMobile.network.request.user;

import com.Cadient.QMobile.model.remote.UserWeight;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

/**
 * Created by ivan on 8/22/14.
 */
public class CreateUserWeightRequest extends AbstractNutrioApiRequest<UserWeight> {

    public CreateUserWeightRequest(UserWeight userWeight) {
        super(UserWeight.class, userWeight);
    }

    @Override
    public UserWeight loadDataFromNetwork() throws Exception {
        return getService().createUserWeights(entity);
    }
}
