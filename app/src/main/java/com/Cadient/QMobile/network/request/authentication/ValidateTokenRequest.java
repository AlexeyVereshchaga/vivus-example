package com.Cadient.QMobile.network.request.authentication;

import com.Cadient.QMobile.model.remote.SessionHandOff;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

import roboguice.util.temp.Ln;

/**
 * Created by alexey on 20.08.14.
 */
public class ValidateTokenRequest extends AbstractNutrioApiRequest<SessionHandOff> {

    public ValidateTokenRequest(SessionHandOff sessionHandOff) {
        super(SessionHandOff.class, sessionHandOff);
    }

    @Override
    public SessionHandOff loadDataFromNetwork() throws Exception {
        Ln.d("Call web service ");
        return getService().validateHandOffToken(entity.getGuid());
    }
}