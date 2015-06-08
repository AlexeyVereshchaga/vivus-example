package com.Cadient.QMobile.view.fragment.mealtrack;

import com.Cadient.QMobile.handler.BaseHandler;
import com.Cadient.QMobile.model.remote.DailyCalorieRemote;
import com.Cadient.QMobile.utils.HelpUtils;
import com.Cadient.QMobile.view.activity.BaseActivity;
import com.Cadient.QMobile.view.fragment.CalendarFragment;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.roomorama.caldroid.CaldroidListener;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Alexey Vereshchaga on 14.11.14.
 */
public abstract class MealCalendarCaldroidListener extends CaldroidListener {
    private RetrofitSpiceRequest request;
    private BaseActivity baseActivity;

    public void setCalendarFragment(CalendarFragment calendarFragment) {
        this.calendarFragment = calendarFragment;
    }

    private CalendarFragment calendarFragment;

    public MealCalendarCaldroidListener(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void onChangeMonth(int month, int year) {

        if (request != null) {
            baseActivity.getSpiceManager().cancel(request);
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.MONTH, month - 1);
        calendar1.set(Calendar.DATE, 1);
        Date startDate = calendar1.getTime();
        calendar1.set(Calendar.DATE, calendar1.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDate = calendar1.getTime();
        RetrofitSpiceRequest request = baseActivity.getDataManager().retrieveDailyCalorieBank(
                startDate, endDate, new BaseHandler<DailyCalorieRemote>(
                        new DailyCalorieRemoteListener(calendarFragment, baseActivity), DailyCalorieRemote.class)
        );
        this.request = request;
    }

    @Override
    public void onCaldroidViewCreated() {
        HelpUtils.constructCalendarWeekdayHeader(baseActivity, calendarFragment);
    }
}
