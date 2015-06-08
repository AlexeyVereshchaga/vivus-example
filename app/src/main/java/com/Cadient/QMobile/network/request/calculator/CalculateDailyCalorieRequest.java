package com.Cadient.QMobile.network.request.calculator;

import com.Cadient.QMobile.model.remote.DailyCalorieTarget;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

import roboguice.util.temp.Ln;

/**
 * Created by alexey on 21.08.14.
 */
public class CalculateDailyCalorieRequest extends AbstractNutrioApiRequest<DailyCalorieTarget> {

    public CalculateDailyCalorieRequest(DailyCalorieTarget dailyCalorieTarget) {
        super(DailyCalorieTarget.class, dailyCalorieTarget);
    }

    @Override
    public DailyCalorieTarget loadDataFromNetwork() throws Exception {
        Ln.d("Call web service ");
        return getService().calculateDailyCalorie(entity);
    }
}
