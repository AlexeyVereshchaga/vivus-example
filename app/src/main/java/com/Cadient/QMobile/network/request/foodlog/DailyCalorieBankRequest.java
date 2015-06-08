package com.Cadient.QMobile.network.request.foodlog;

import com.Cadient.QMobile.model.remote.DailyCalorieRemote;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;
import com.Cadient.QMobile.utils.HelpUtils;

import java.util.Date;

/**
 * Created by Alexey Vereshchaga on 04.09.14.
 */
public class DailyCalorieBankRequest extends AbstractNutrioApiRequest<DailyCalorieRemote> {

    private Date startDate;
    private Date endDate;

    public DailyCalorieBankRequest(Date startDate, Date endDate) {
        super(DailyCalorieRemote.class);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public DailyCalorieRemote loadDataFromNetwork() throws Exception {
        return getService().retrieveDailyCalorieBank(HelpUtils.dateToInteger(startDate), HelpUtils.dateToInteger(endDate));
    }
}
