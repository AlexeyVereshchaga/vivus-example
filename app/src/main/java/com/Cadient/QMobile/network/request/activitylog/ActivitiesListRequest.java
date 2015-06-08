package com.Cadient.QMobile.network.request.activitylog;

import com.Cadient.QMobile.model.remote.ActivitiesList;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;
import com.Cadient.QMobile.utils.HelpUtils;

import java.util.Date;

/**
 * Created by Alexey Vereshchaga on 13.10.14.
 */
public class ActivitiesListRequest extends AbstractNutrioApiRequest<ActivitiesList> {

    private Date date;

    public ActivitiesListRequest(Date date) {
        super(ActivitiesList.class);
        this.date = date;
    }

    @Override
    public ActivitiesList loadDataFromNetwork() throws Exception {
        return getService().getActivitiesList(HelpUtils.dateToInteger(date));
    }
}
