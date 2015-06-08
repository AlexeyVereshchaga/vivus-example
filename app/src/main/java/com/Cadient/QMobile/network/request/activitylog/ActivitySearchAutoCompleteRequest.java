package com.Cadient.QMobile.network.request.activitylog;

import com.Cadient.QMobile.model.Activity;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

/**
 * Created by ivan on 9/2/14.
 */
public class ActivitySearchAutoCompleteRequest extends AbstractNutrioApiRequest<Activity[]> {

    private String term;

    public ActivitySearchAutoCompleteRequest(String term) {
        super(Activity[].class);
        this.term = term;
    }

    @Override
    public Activity[] loadDataFromNetwork() throws Exception {
        return getService().suggestActivities(term);
    }
}
