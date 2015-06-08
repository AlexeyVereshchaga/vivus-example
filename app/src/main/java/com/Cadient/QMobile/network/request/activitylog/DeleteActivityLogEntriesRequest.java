package com.Cadient.QMobile.network.request.activitylog;

import com.Cadient.QMobile.model.remote.ActivityLogEntries;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

/**
 * Created by ivan on 9/2/14.
 */
public class DeleteActivityLogEntriesRequest extends AbstractNutrioApiRequest<ActivityLogEntries> {

    public DeleteActivityLogEntriesRequest(ActivityLogEntries entries) {
        super(ActivityLogEntries.class, entries);
    }

    @Override
    public ActivityLogEntries loadDataFromNetwork() throws Exception {
        return getService().deleteActivityLogEntries(entity.getId());
    }
}
