package com.Cadient.QMobile.network.request.cookery;

import com.Cadient.QMobile.model.remote.MealTranslator;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

import roboguice.util.temp.Ln;

/**
 * Created by Alexey Vereshchaga on 04.09.14.
 */
public class MealIdTranslationsRequest extends AbstractNutrioApiRequest<MealTranslator> {

    public MealIdTranslationsRequest(MealTranslator entity) {
        super(MealTranslator.class, entity);

    }

    @Override
    public MealTranslator loadDataFromNetwork() throws Exception {
        Ln.d("Call web service ");
        return getService().translateMealId(entity);
    }
}
