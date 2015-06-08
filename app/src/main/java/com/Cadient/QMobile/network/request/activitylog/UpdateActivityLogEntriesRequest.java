package com.Cadient.QMobile.network.request.activitylog;

import com.Cadient.QMobile.model.remote.ActivityLogEntries;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

/**
 * Created by ivan on 9/2/14.
 */
public class UpdateActivityLogEntriesRequest extends AbstractNutrioApiRequest<ActivityLogEntries> {

    public UpdateActivityLogEntriesRequest(ActivityLogEntries entries) {
        super(ActivityLogEntries.class, entries);
    }

    @Override
    public ActivityLogEntries loadDataFromNetwork() throws Exception {
        return getService().updateActivityLogEntries(entity.getId(), entity);
    }
}
