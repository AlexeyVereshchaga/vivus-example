package com.Cadient.QMobile.network.request.authentication;

import com.Cadient.QMobile.model.remote.RegistrationResult;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

import roboguice.util.temp.Ln;

public class RegistrationResultRequest extends AbstractNutrioApiRequest<RegistrationResult> {

    public RegistrationResultRequest() {
        super(RegistrationResult.class);
    }

    @Override
    public RegistrationResult loadDataFromNetwork() {
        Ln.d("Call web service ");
        return getService().registrationResult();
    }
}
