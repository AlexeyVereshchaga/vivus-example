package com.Cadient.QMobile.network.request.authentication;

import com.Cadient.QMobile.model.remote.SessionHandOff;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

import roboguice.util.temp.Ln;

/**
 * Created by alexey on 20.08.14.
 */
public class GenerateTokenRequest extends AbstractNutrioApiRequest<SessionHandOff> {

    public GenerateTokenRequest(Integer userId) {
        super(SessionHandOff.class, new SessionHandOff(userId));
    }

    @Override
    public SessionHandOff loadDataFromNetwork() throws Exception {
        Ln.d("Call web service ");
        return getService().generateHandOffToken(entity);
    }
}
