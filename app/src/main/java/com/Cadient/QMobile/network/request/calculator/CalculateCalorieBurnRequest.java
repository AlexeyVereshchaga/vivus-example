package com.Cadient.QMobile.network.request.calculator;

import com.Cadient.QMobile.model.remote.CalorieBurnCalculator;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

import roboguice.util.temp.Ln;

/**
 * Created by alexey on 21.08.14.
 */
public class CalculateCalorieBurnRequest extends AbstractNutrioApiRequest<CalorieBurnCalculator> {

    public CalculateCalorieBurnRequest(CalorieBurnCalculator calorieBurnCalculator) {
        super(CalorieBurnCalculator.class, calorieBurnCalculator);
    }

    @Override
    public CalorieBurnCalculator loadDataFromNetwork() throws Exception {
        Ln.d("Call web service ");
        return getService().calculateBurnCalorie(entity);
    }
}
