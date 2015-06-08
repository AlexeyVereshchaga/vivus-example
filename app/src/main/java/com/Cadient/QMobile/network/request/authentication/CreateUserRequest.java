package com.Cadient.QMobile.network.request.authentication;

import com.Cadient.QMobile.model.remote.UserRemote;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

import roboguice.util.temp.Ln;

public class CreateUserRequest extends AbstractNutrioApiRequest<UserRemote> {

    public CreateUserRequest(UserRemote userRemote) {
        super(UserRemote.class, userRemote);
    }

    @Override
    public UserRemote loadDataFromNetwork() {
        Ln.d("Call web service ");
        return getService().createUser(entity);
    }
}
