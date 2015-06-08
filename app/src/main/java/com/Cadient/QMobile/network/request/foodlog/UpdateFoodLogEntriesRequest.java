package com.Cadient.QMobile.network.request.foodlog;

import com.Cadient.QMobile.model.MealForLog;
import com.Cadient.QMobile.model.remote.FoodLogEntries;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

/**
 * Created by ivan on 9/2/14.
 */
public class UpdateFoodLogEntriesRequest extends AbstractNutrioApiRequest<FoodLogEntries> {
    private MealForLog mealForLog;

    public UpdateFoodLogEntriesRequest(MealForLog mealForLog) {
        super(FoodLogEntries.class);
        this.mealForLog = mealForLog;
    }

    @Override
    public FoodLogEntries loadDataFromNetwork() throws Exception {
        return getService().updateFoodLogEntries(mealForLog.getId(), mealForLog);
    }
}
