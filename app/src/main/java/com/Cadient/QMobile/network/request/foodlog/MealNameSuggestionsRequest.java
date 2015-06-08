package com.Cadient.QMobile.network.request.foodlog;

import com.Cadient.QMobile.model.remote.SuggestedMeal;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

/**
 * Created by Alexey Vereshchaga on 04.09.14.
 */
public class MealNameSuggestionsRequest extends AbstractNutrioApiRequest<SuggestedMeal> {

    public MealNameSuggestionsRequest(SuggestedMeal entity) {
        super(SuggestedMeal.class, entity);
    }

    @Override
    public SuggestedMeal loadDataFromNetwork() throws Exception {
        return getService().suggestMealName(entity);
    }
}
