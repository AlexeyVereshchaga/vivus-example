package com.Cadient.QMobile.network.request.cookery;

import com.Cadient.QMobile.model.SearchByKeyword;
import com.Cadient.QMobile.model.SearchModel;
import com.Cadient.QMobile.model.remote.RecipeRemote;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

import roboguice.util.temp.Ln;

/**
 * Created by Alexey Vereshchaga on 03.09.14.
 */
public class SearchRecipeByKeywordsRequest extends AbstractNutrioApiRequest<RecipeRemote> {

    private SearchModel entity;

    public SearchRecipeByKeywordsRequest(SearchByKeyword entity) {
        super(RecipeRemote.class, null);
        this.entity = entity;
    }

    @Override
    public RecipeRemote loadDataFromNetwork() throws Exception {
        Ln.d("Call web service ");
        return getService().searchRecipeByKeywords(entity);
    }
}
