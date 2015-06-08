package com.Cadient.QMobile.network.request.weightlog;

import com.Cadient.QMobile.model.remote.WeightTrackerEntry;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

/**
 * Created by ivan on 8/22/14.
 */
public class DeleteWeightTrackerEntryRequest extends AbstractNutrioApiRequest<WeightTrackerEntry> {

    public DeleteWeightTrackerEntryRequest(WeightTrackerEntry weightTrackerEntry) {
        super(WeightTrackerEntry.class, weightTrackerEntry);
    }

    @Override
    public WeightTrackerEntry loadDataFromNetwork() throws Exception {
        return getService().deleteWeightTrackerEntry(entity.getId());
    }
}
