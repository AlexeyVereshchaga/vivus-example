package com.Cadient.QMobile.network.request.foodlog;

import com.Cadient.QMobile.model.remote.ParserRemote;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

/**
 * Created by Alexey Vereshchaga on 04.09.14.
 */
public class ParseNaturalLanguageRequest extends AbstractNutrioApiRequest<ParserRemote> {

    public ParseNaturalLanguageRequest(ParserRemote entity) {
        super(ParserRemote.class, entity);
    }

    @Override
    public ParserRemote loadDataFromNetwork() throws Exception {
        return getService().parseNaturalLanguage(entity);
    }
}
