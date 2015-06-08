package com.Cadient.QMobile.network.request.user;

import com.Cadient.QMobile.model.remote.WeightLossGoalCycles;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

/**
 * Created by ivan on 8/25/14.
 */
public class UpdateUserWeightLossRequest extends AbstractNutrioApiRequest<WeightLossGoalCycles> {

    public UpdateUserWeightLossRequest(WeightLossGoalCycles weightLossGoalCycles) {
        super(WeightLossGoalCycles.class, weightLossGoalCycles);
    }

    @Override
    public WeightLossGoalCycles loadDataFromNetwork() throws Exception {
        return getService().updateUserWeightLoss(entity.getId(), entity);
    }
}
