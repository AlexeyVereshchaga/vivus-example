package com.Cadient.QMobile.network.request.user;

import com.Cadient.QMobile.model.remote.FavoriteUserMeal;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

/**
 * Created by ivan on 8/22/14.
 */
public class DeleteFavoriteUserMealRequest extends AbstractNutrioApiRequest<FavoriteUserMeal> {

    public DeleteFavoriteUserMealRequest(FavoriteUserMeal favoriteUserMeal) {
        super(FavoriteUserMeal.class, favoriteUserMeal);
    }

    @Override
    public FavoriteUserMeal loadDataFromNetwork() throws Exception {
        return getService().deleteFavoriteUserMeal(entity.getId());
    }
}
