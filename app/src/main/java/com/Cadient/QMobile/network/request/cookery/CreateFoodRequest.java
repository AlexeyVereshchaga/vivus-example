package com.Cadient.QMobile.network.request.cookery;

import com.Cadient.QMobile.model.remote.FoodRemote;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

import roboguice.util.temp.Ln;

/**
 * Created by Alexey Vereshchaga on 01.09.14.
 */
public class CreateFoodRequest extends AbstractNutrioApiRequest<FoodRemote> {

    public CreateFoodRequest(FoodRemote food) {
        super(FoodRemote.class, food);
    }

    @Override
    public FoodRemote loadDataFromNetwork() throws Exception {
        Ln.d("Call web service ");
        return getService().createFood(entity);
    }
}
