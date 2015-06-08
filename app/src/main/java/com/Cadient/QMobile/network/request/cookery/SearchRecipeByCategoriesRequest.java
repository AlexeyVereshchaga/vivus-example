package com.Cadient.QMobile.network.request.cookery;

import com.Cadient.QMobile.model.SearchByCategory;
import com.Cadient.QMobile.model.SearchModel;
import com.Cadient.QMobile.model.remote.RecipeRemote;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

/**
 * Created by Alexey Vereshchaga on 03.09.14.
 */
public class SearchRecipeByCategoriesRequest extends AbstractNutrioApiRequest<RecipeRemote> {

    private SearchModel entity;

    public SearchRecipeByCategoriesRequest(SearchByCategory entity) {
        super(RecipeRemote.class, null);
        this.entity = entity;
    }

    @Override
    public RecipeRemote loadDataFromNetwork() throws Exception {
        return getService().searchRecipeByCategories(entity);
    }
}
