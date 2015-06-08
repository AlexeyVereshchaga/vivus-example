package com.Cadient.QMobile.network.request.foodlog;

import com.Cadient.QMobile.model.remote.FoodLogEntries;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

/**
 * Created by ivan on 9/2/14.
 */
public class ReadFoodLogEntriesRequest extends AbstractNutrioApiRequest<FoodLogEntries> {

    public ReadFoodLogEntriesRequest(FoodLogEntries entries) {
        super(FoodLogEntries.class, entries);
    }

    @Override
    public FoodLogEntries loadDataFromNetwork() throws Exception {
        return getService().readFoodLogEntries(entity.getId());
    }
}
