package com.Cadient.QMobile.network.request.foodlog;

import com.Cadient.QMobile.model.remote.RecipeRemote;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

/**
 * Created by Alexey Vereshchaga on 24.10.14.
 */
public class FavoriteMealsRequest extends AbstractNutrioApiRequest<RecipeRemote> {

    public FavoriteMealsRequest() {
        super(RecipeRemote.class);
    }

    @Override
    public RecipeRemote loadDataFromNetwork() throws Exception {
        return getService().obtainFavoriteMeals();
    }
}
