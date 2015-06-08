package com.Cadient.QMobile.network.request.foodlog;

import com.Cadient.QMobile.model.remote.FoodLogList;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;
import com.Cadient.QMobile.utils.HelpUtils;

import java.util.Date;

/**
 * Created by Alex on 27.10.2014.
 */
public class FoodLogDateRequest extends AbstractNutrioApiRequest<FoodLogList> {

    private Date date;

    public FoodLogDateRequest(Date date) {
        super(FoodLogList.class);
        this.date = date;
    }

    @Override
    public FoodLogList loadDataFromNetwork() throws Exception {
        return getService().readFoodLogForDate(HelpUtils.parseDateToString(date, HelpUtils.DATE_FORMAT));
    }
}
