package com.Cadient.QMobile.network.request.cookery;

import com.Cadient.QMobile.model.FoodListReadModel;
import com.Cadient.QMobile.model.remote.FoodList;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

import roboguice.util.temp.Ln;

/**
 * Created by Alexey Vereshchaga on 02.09.14.
 */
public class ReadListOfFoodRequest extends AbstractNutrioApiRequest<FoodList> {

    private FoodListReadModel readModel;

    public ReadListOfFoodRequest() {
        super(FoodList.class, null);
        readModel = new FoodListReadModel();
    }

    public ReadListOfFoodRequest(FoodListReadModel readModel) {
        super(FoodList.class, null);
        this.readModel = readModel;
    }

    @Override
    public FoodList loadDataFromNetwork() throws Exception {
        Ln.d("Call web service ");
        return getService().readListOfFood(readModel.getOptionsMap());
    }
}
