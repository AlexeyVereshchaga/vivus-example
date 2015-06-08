package com.Cadient.QMobile.network.request.cookery;

import com.Cadient.QMobile.model.remote.FoodRemote;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

import roboguice.util.temp.Ln;

/**
 * Created by Alexey Vereshchaga on 02.09.14.
 */
public class UpdateFoodRequest extends AbstractNutrioApiRequest<FoodRemote> {

    public UpdateFoodRequest(FoodRemote food) {
        super(FoodRemote.class, food);
    }

    @Override
    public FoodRemote loadDataFromNetwork() throws Exception {
        Ln.d("Call web service ");
        return getService().updateFood(entity.getFood().getId(), entity);
    }
}
