package com.Cadient.QMobile.network.request.user;

import com.Cadient.QMobile.model.remote.MealRating;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

/**
 * Created by ivan on 8/22/14.
 */
public class ReadMealRatingRequest extends AbstractNutrioApiRequest<MealRating> {

    public ReadMealRatingRequest(MealRating mealRating) {
        super(MealRating.class, mealRating);
    }

    @Override
    public MealRating loadDataFromNetwork() throws Exception {
        return getService().readUserMealRating(entity.getMealId());
    }
}
