package com.Cadient.QMobile.network.request.authentication;

import com.Cadient.QMobile.model.remote.UserRemote;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

import roboguice.util.temp.Ln;

/**
 * Created by alexey on 15.08.14.
 */
public class ReadUserRequest extends AbstractNutrioApiRequest<UserRemote> {

    public ReadUserRequest(UserRemote userRemote) {
        super(UserRemote.class, userRemote);
    }

    @Override
    public UserRemote loadDataFromNetwork() throws Exception {
        Ln.d("Call web service ");
        return getService().readUser(entity.getInternalUserId());
    }
}
