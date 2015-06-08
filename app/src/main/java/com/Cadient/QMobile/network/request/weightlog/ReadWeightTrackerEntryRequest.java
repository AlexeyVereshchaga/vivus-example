package com.Cadient.QMobile.network.request.weightlog;

import com.Cadient.QMobile.model.remote.WeightTrackerEntries;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

/**
 * Created by ivan on 8/22/14.
 */
public class ReadWeightTrackerEntryRequest extends AbstractNutrioApiRequest<WeightTrackerEntries> {

    public ReadWeightTrackerEntryRequest() {
        super(WeightTrackerEntries.class);
    }

    @Override
    public WeightTrackerEntries loadDataFromNetwork() throws Exception {
        return getService().readWeightTrackerEntry();
    }
}
