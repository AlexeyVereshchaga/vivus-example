package com.Cadient.QMobile.network.request.authentication;

import com.Cadient.QMobile.model.remote.UserRemote;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

import roboguice.util.temp.Ln;

/**
 * Created by alexey on 18.08.14.
 */
public class SearchUserRequest extends AbstractNutrioApiRequest<UserRemote> {

    public SearchUserRequest(UserRemote userRemote) {
        super(UserRemote.class, userRemote);
    }

    @Override
    public UserRemote loadDataFromNetwork() throws Exception {
        Ln.d("Call web service ");
        return getService().searchUser(entity.getExternalUserId());
    }
}
