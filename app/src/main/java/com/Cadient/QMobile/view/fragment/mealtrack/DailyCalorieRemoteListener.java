package com.Cadient.QMobile.view.fragment.mealtrack;

import com.Cadient.QMobile.R;
import com.Cadient.QMobile.handler.BaseRemoteListener;
import com.Cadient.QMobile.model.DailyCalorie;
import com.Cadient.QMobile.model.remote.DailyCalorieRemote;
import com.Cadient.QMobile.utils.HelpUtils;
import com.Cadient.QMobile.view.activity.BaseActivity;
import com.Cadient.QMobile.view.fragment.CalendarFragment;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Alexey Vereshchaga on 14.11.14.
 */
public class DailyCalorieRemoteListener extends BaseRemoteListener<DailyCalorieRemote> {

    private CalendarFragment calendarFragment;
    private BaseActivity baseActivity;

    public DailyCalorieRemoteListener(CalendarFragment calendarFragment, BaseActivity baseActivity) {
        super(baseActivity);
        this.calendarFragment = calendarFragment;
        this.baseActivity = baseActivity;
    }

    @Override
    public void onSuccess(DailyCalorieRemote result) {
        List<DailyCalorie> dailyCalorieList = result.getDailyCalorieList();
        HashMap<Date, Integer> dateIntegerMap = new HashMap<Date, Integer>();
        String currentDate = HelpUtils.parseDateToString(Calendar.getInstance().getTime(), HelpUtils.DATE_FORMAT);
        for (DailyCalorie dailyCalorie : dailyCalorieList) {
            BigDecimal consumedCalories = dailyCalorie.getCaloriesConsumed();
            String dateStr = dailyCalorie.getDate().toString();
            if (consumedCalories != null && consumedCalories.compareTo(BigDecimal.ZERO) != 0 && !dateStr.equals(currentDate)) {
                dateIntegerMap.put(HelpUtils.parseStringToDate(dateStr), R.drawable.calendar_activity_date);
            }
        }
        calendarFragment.setBackgroundResourceForDates(dateIntegerMap);
        boolean compareDay = HelpUtils.compareDateByDay(baseActivity.getCalendar().getTimeInMillis(), Calendar.getInstance().getTimeInMillis());
        calendarFragment.setBackgroundResourceForDate(compareDay
                ? R.drawable.calendar_current_date : R.drawable.calendar_selected_date, baseActivity.getCalendar().getTime());
        calendarFragment.refreshView();
    }
}
