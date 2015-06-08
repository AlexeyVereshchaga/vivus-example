package com.Cadient.QMobile.network.request.weightlog;

import com.Cadient.QMobile.model.remote.WeightTrackerEntry;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

/**
 * Created by ivan on 8/22/14.
 */
public class CreateWeightTrackerEntryRequest extends AbstractNutrioApiRequest<WeightTrackerEntry> {

    public CreateWeightTrackerEntryRequest(WeightTrackerEntry weightTrackerEntry) {
        super(WeightTrackerEntry.class, weightTrackerEntry);
    }

    @Override
    public WeightTrackerEntry loadDataFromNetwork() throws Exception {
        return getService().createWeightTrackerEntry(entity);
    }
}
