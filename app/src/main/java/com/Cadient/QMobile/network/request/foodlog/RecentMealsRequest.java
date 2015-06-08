package com.Cadient.QMobile.network.request.foodlog;

import com.Cadient.QMobile.model.remote.SuggestedMeal;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

/**
 * Created by Alexey Vereshchaga on 24.10.14.
 */
public class RecentMealsRequest extends AbstractNutrioApiRequest<SuggestedMeal> {

    public RecentMealsRequest() {
        super(SuggestedMeal.class);
    }

    @Override
    public SuggestedMeal loadDataFromNetwork() throws Exception {
        return getService().obtainRecentMeals();
    }
}
