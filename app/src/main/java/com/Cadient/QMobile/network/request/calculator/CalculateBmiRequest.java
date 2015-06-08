package com.Cadient.QMobile.network.request.calculator;

import com.Cadient.QMobile.model.remote.BmiCalculator;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

import roboguice.util.temp.Ln;

/**
 * Created by alexey on 20.08.14.
 */
public class CalculateBmiRequest extends AbstractNutrioApiRequest<BmiCalculator> {

    public CalculateBmiRequest(BmiCalculator bmiCalculator) {
        super(BmiCalculator.class, bmiCalculator);
    }

    @Override
    public BmiCalculator loadDataFromNetwork() throws Exception {
        Ln.d("Call web service ");
        return getService().calculateBmi(entity);
    }
}
