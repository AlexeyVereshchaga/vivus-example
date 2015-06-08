package com.Cadient.QMobile.network.request.activitylog;

import com.Cadient.QMobile.model.remote.ActivityLogEntries;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

/**
 * Created by ivan on 9/2/14.
 */
public class CreateActivityLogEntriesRequest extends AbstractNutrioApiRequest<ActivityLogEntries> {

    public CreateActivityLogEntriesRequest(ActivityLogEntries entries) {
        super(ActivityLogEntries.class, entries);
    }

    @Override
    public ActivityLogEntries loadDataFromNetwork() throws Exception {
        return getService().createActivityLogEntries(entity);
    }
}
