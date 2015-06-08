package com.Cadient.QMobile.network.request.foodlog;

import com.Cadient.QMobile.model.remote.FoodLogCurrent;
import com.Cadient.QMobile.model.remote.FoodLogEntries;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;
import com.Cadient.QMobile.utils.HelpUtils;

import java.util.Date;

/**
 * Created by Alexey Vereshchaga on 23.10.14.
 */
public class TodayFoodLogRequest extends AbstractNutrioApiRequest<FoodLogCurrent> {

    private Date date;

    public TodayFoodLogRequest(Date date) {
        super(FoodLogCurrent.class);
        this.date = date;
    }

    @Override
    public FoodLogCurrent loadDataFromNetwork() throws Exception {
        return getService().obtainTodayFoodLog(HelpUtils.dateToInteger(date));
    }
}
