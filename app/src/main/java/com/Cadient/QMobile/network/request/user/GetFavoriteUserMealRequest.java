package com.Cadient.QMobile.network.request.user;

import com.Cadient.QMobile.model.remote.FavoriteUserMeal;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

/**
 * Created by ivan on 8/22/14.
 */
public class GetFavoriteUserMealRequest extends AbstractNutrioApiRequest<FavoriteUserMeal> {

    public GetFavoriteUserMealRequest(FavoriteUserMeal favoriteUserMeal) {
        super(FavoriteUserMeal.class, favoriteUserMeal);
    }

    @Override
    public FavoriteUserMeal loadDataFromNetwork() throws Exception {
        return getService().getFavoriteUserMeal(entity.getMealId());
    }
}
