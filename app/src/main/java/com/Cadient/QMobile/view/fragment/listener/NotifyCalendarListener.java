package com.Cadient.QMobile.view.fragment.listener;

import android.view.View;

import com.Cadient.QMobile.R;
import com.Cadient.QMobile.view.activity.BaseActivity;

import java.util.Calendar;

/**
 * Created by ivan on 11/7/14.
 */
public abstract class NotifyCalendarListener implements View.OnClickListener {
    private BaseActivity activity;

    protected NotifyCalendarListener(BaseActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.header_arrow_left:
                activity.getCalendar().add(Calendar.DATE, -1);
                activity.notifyArrows();
                break;
            case R.id.header_arrow_right:
                activity.getCalendar().add(Calendar.DATE, 1);
                activity.notifyArrows();
                break;
        }
    }
}
