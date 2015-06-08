package com.Cadient.QMobile.network.request.foodlog;

import com.Cadient.QMobile.model.remote.FoodLogNutrients;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

/**
 * Created by ivan on 9/2/14.
 */
public class GetFoodLogNutrientsRequest extends AbstractNutrioApiRequest<FoodLogNutrients> {

    public GetFoodLogNutrientsRequest(FoodLogNutrients nutrients) {
        super(FoodLogNutrients.class, nutrients);
    }

    @Override
    public FoodLogNutrients loadDataFromNetwork() throws Exception {
        return getService().getFoodLogNutrients(entity.getId());
    }
}
