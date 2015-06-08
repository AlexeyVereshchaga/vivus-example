package com.Cadient.QMobile.network.request.cookery;

import com.Cadient.QMobile.model.remote.FoodRemote;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

import roboguice.util.temp.Ln;

/**
 * Created by Alexey Vereshchaga on 02.09.14.
 */
public class DeleteFoodRequest extends AbstractNutrioApiRequest<FoodRemote> {

    private Integer foodId;

    public DeleteFoodRequest(Integer foodId) {
        super(FoodRemote.class, null);
        this.foodId = foodId;
    }

    @Override
    public FoodRemote loadDataFromNetwork() throws Exception {
        Ln.d("Call web service ");
        return getService().deleteFood(foodId);
    }
}
