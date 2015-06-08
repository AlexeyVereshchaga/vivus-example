package com.Cadient.QMobile.network.request;

import com.Cadient.QMobile.network.NutrioApi;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by alexey on 26.08.14.
 */
public abstract class AbstractNutrioApiRequest<T> extends RetrofitSpiceRequest<T, NutrioApi> {

    protected T entity;

    public AbstractNutrioApiRequest(Class<T> clazz, T entity) {
        super(clazz, NutrioApi.class);
        this.entity = entity;
    }

    public AbstractNutrioApiRequest(Class<T> clazz) {
        super(clazz, NutrioApi.class);
    }

    /**
     * This method generates a unique cache key for this request. In this case
     * our cache key depends just on the keyword.
     *
     * @return
     */
    public String createCacheKey() {
        return "entity." + entity;
    }
}
