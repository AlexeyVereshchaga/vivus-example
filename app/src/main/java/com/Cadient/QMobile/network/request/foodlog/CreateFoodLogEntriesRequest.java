package com.Cadient.QMobile.network.request.foodlog;

import com.Cadient.QMobile.model.MealForLog;
import com.Cadient.QMobile.model.remote.FoodLogCreatedEntry;
import com.Cadient.QMobile.model.remote.FoodLogEntries;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

/**
 * Created by ivan on 9/2/14.
 */
public class CreateFoodLogEntriesRequest extends AbstractNutrioApiRequest<FoodLogCreatedEntry> {

    private MealForLog meal;

    public CreateFoodLogEntriesRequest(MealForLog meal) {
        super(FoodLogCreatedEntry.class);
        this.meal = meal;
    }

    @Override
    public FoodLogCreatedEntry loadDataFromNetwork() throws Exception {
        return getService().createFoodLogEntries(meal);
    }
}
