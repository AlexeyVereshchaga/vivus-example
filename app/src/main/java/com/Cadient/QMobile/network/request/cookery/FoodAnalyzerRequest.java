package com.Cadient.QMobile.network.request.cookery;

import com.Cadient.QMobile.model.SearchByKeyword;
import com.Cadient.QMobile.model.SearchModel;
import com.Cadient.QMobile.model.remote.RecipeRemote;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

import roboguice.util.temp.Ln;

/**
 * Created by Alexey Vereshchaga on 04.09.14.
 */
public class FoodAnalyzerRequest extends AbstractNutrioApiRequest<RecipeRemote> {
    private SearchModel entity;

    public FoodAnalyzerRequest(SearchByKeyword entity) {
        super(RecipeRemote.class, null);
        this.entity = entity;
    }

    @Override
    public RecipeRemote loadDataFromNetwork() throws Exception {
        Ln.d("Call web service ");
        return getService().analyzeFood(entity);
    }
}
