package com.Cadient.QMobile.view.fragment.mealtrack;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.Cadient.QMobile.R;
import com.Cadient.QMobile.handler.BaseHandler;
import com.Cadient.QMobile.handler.BaseRemoteListener;
import com.Cadient.QMobile.model.remote.FoodLogCurrent;
import com.Cadient.QMobile.model.remote.FoodLogEntries;
import com.Cadient.QMobile.utils.HelpUtils;
import com.Cadient.QMobile.view.activity.BaseActivity;
import com.Cadient.QMobile.view.activity.MainActivity;
import com.Cadient.QMobile.view.fragment.AbstractFragment;
import com.Cadient.QMobile.view.fragment.CalendarFragment;
import com.Cadient.QMobile.view.fragment.IDataChangeListener;
import com.Cadient.QMobile.view.fragment.listener.NotifyCalendarListener;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ivan on 10/21/14.
 */
public class MealLogSelectCategoryFragment extends AbstractFragment<MainActivity> implements View.OnClickListener {

    public static final String BUNDLE_KEY_DETAIL = "meal_slot_id";
    private Calendar calendarLocal;

    private IDataChangeListener dataChangeListener;

    private RetrofitSpiceRequest request;

    static class ViewHolder {
        public LinearLayout breakfast, lunch, dinner, snacks;
        public ImageView icBreakfast, icLunch, icDinner, icSnacks;
    }

    private List<FoodLogEntries> foodLogEntries;
    private List<FoodLogEntries> logEntriesList;
    private ViewHolder holder;

    private CalendarFragment calendarFragment = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void assignViews(View view) {
        holder = new ViewHolder();
        holder.breakfast = (LinearLayout) view.findViewById(R.id.ll_breakfast);
        holder.lunch = (LinearLayout) view.findViewById(R.id.ll_lunch);
        holder.dinner = (LinearLayout) view.findViewById(R.id.ll_dinner);
        holder.snacks = (LinearLayout) view.findViewById(R.id.ll_snacks);

        holder.icBreakfast = (ImageView) view.findViewById(R.id.img_breakfast);
        holder.icLunch = (ImageView) view.findViewById(R.id.img_lunch);
        holder.icDinner = (ImageView) view.findViewById(R.id.img_dinner);
        holder.icSnacks = (ImageView) view.findViewById(R.id.img_snacks);
    }

    @Override
    protected int getViewLayout() {
        return R.layout.fragm_select_category;
    }

    @Override
    protected void initView(View view) {
        defaultStateView();
        Calendar calendar = Calendar.getInstance();
        if (getArguments() != null) {
            calendar.setTimeInMillis(getArguments().getLong(HelpUtils.MEAL_LOG_CURRENT_DATE_BUNDLE_KEY));
            calendarLocal = calendar;
        }
        if (dataChangeListener != null) {
            dataChangeListener.onChanged(calendar.getTime());
        }
    }

    private void setOnClickListenersTag(LinearLayout... layout) {
        for (LinearLayout linearLayout : layout) {
            linearLayout.setOnClickListener(this);
            linearLayout.setTag("0");
        }
    }

    private void setDefaultImageRes(ImageView... imageViews) {
        for (ImageView imageView : imageViews) {
            imageView.setImageResource(R.drawable.food);
        }
    }

    private void updateView() {
        defaultStateView();
        for (FoodLogEntries logEntries : foodLogEntries) {
            if (logEntries.getSlotId() == 1) {
                holder.breakfast.setTag("1");
                holder.icBreakfast.setImageResource(R.drawable.food_select);
            } else if (logEntries.getSlotId() == 3) {
                holder.lunch.setTag("3");
                holder.icLunch.setImageResource(R.drawable.food_select);
            } else if (logEntries.getSlotId() == 5) {
                holder.dinner.setTag("5");
                holder.icDinner.setImageResource(R.drawable.food_select);
            } else {
                holder.snacks.setTag("9");
                holder.icSnacks.setImageResource(R.drawable.food_select);
            }
        }
    }

    private void defaultStateView() {
        setOnClickListenersTag(holder.breakfast, holder.lunch, holder.dinner, holder.snacks);
        setDefaultImageRes(holder.icBreakfast, holder.icLunch, holder.icDinner, holder.icSnacks);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (calendarLocal != null) {
            getBaseActivity().setCalendar(calendarLocal);
        }
        sendFoodLogRequest();
        initHeader();
    }

    private void sendFoodLogRequest() {
        if (request != null) {
            getBaseActivity().getSpiceManager().cancel(request);
        }
        request = getBaseActivity().getDataManager().obtainTodayFoodLog(getBaseActivity().getCalendar().getTime(),
                new BaseHandler<FoodLogCurrent>(new TodayFoodLogListener(getActivity()), FoodLogCurrent.class));
    }

    @Override
    public void onPause() {
        super.onPause();
        getArguments().putLong(HelpUtils.MEAL_LOG_CURRENT_DATE_BUNDLE_KEY, getBaseActivity().getCalendar().getTimeInMillis());
        getBaseActivity().getDrawer().setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    private void initHeader() {
        if (getHeaderController() != null) {
            getHeaderController().setLeftButton(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getTransitManager().back();
                }
            }, true, R.drawable.arrow_back);
//            getHeaderController().setTitle(getString(R.string.select_category_title));
            getHeaderController().setArrowLeft(new ArrowListener(getBaseActivity()), true);
            getHeaderController().setArrowRight(new ArrowListener(getBaseActivity()), true);

            getHeaderController().setRightButton(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initCalendar();
                }
            }, true, R.drawable.exercise_calendar);
        }
        getBaseActivity().getDrawer().setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    private class ArrowListener extends NotifyCalendarListener {

        protected ArrowListener(BaseActivity activity) {
            super(activity);
        }

        @Override
        public void onClick(View v) {
            super.onClick(v);
            defaultStateView();
            sendFoodLogRequest();
            if (dataChangeListener != null) {
                dataChangeListener.onChanged(getBaseActivity().getCalendar().getTime());
            }
        }
    }

    private void initCalendar() {
        calendarFragment = HelpUtils.constructCalendarFragment(getBaseActivity().getCalendar(), null);
        CalendarCaldroidListener calendarCaldroidListener = new CalendarCaldroidListener(getBaseActivity());
        calendarCaldroidListener.setCalendarFragment(calendarFragment);
        calendarFragment.setCaldroidListener(calendarCaldroidListener);
        FragmentTransaction t = getBaseActivity().getSupportFragmentManager().beginTransaction();
        t.replace(R.id.fragment_container, calendarFragment);
        t.addToBackStack("0");
        t.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_breakfast:
                if (v.getTag().equals("1") && findEntriesByCategory(1).length != 0) {
                    startDetailFragment(dataToBundle(findEntriesByCategory(1)));
                } else {
                    Toast.makeText(getActivity(), "No breakfasts logged. Log will become available once a meal has been added.", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.ll_lunch:
                if (v.getTag().equals("3") && findEntriesByCategory(3).length != 0) {
                    startDetailFragment(dataToBundle(findEntriesByCategory(3)));
                } else {
                    Toast.makeText(getActivity(), "No lunches logged. Log will become available once a meal has been added.", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.ll_dinner:
                if (v.getTag().equals("5") && findEntriesByCategory(5).length != 0) {
                    startDetailFragment(dataToBundle(findEntriesByCategory(5)));
                } else {
                    Toast.makeText(getActivity(), "No dinners logged. Log will become available once a meal has been added.", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.ll_snacks:
                if (v.getTag().equals("9") && findEntriesByCategory(-1).length != 0) {
                    startDetailFragment(dataToBundle(findEntriesByCategory(-1)));
                } else {
                    Toast.makeText(getActivity(), "No snacks logged. Log will become available once a meal has been added.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void startDetailFragment(Bundle bundle) {
        MealLogCategoryDetailFragment fragment = new MealLogCategoryDetailFragment();
        fragment.setArguments(bundle);
        if (dataChangeListener != null) {
            fragment.setDataChangeListener(dataChangeListener);
        }
        FragmentTransaction t = getBaseActivity().getSupportFragmentManager().beginTransaction();
        t.replace(R.id.fragment_container, fragment);
        t.addToBackStack("0");
        t.commit();
    }

    private FoodLogEntries[] findEntriesByCategory(int number) {
        logEntriesList = new ArrayList<FoodLogEntries>();
        if (number == -1) {
            for (FoodLogEntries entries : foodLogEntries) {
                if (entries.getSlotId() != 1 & entries.getSlotId() != 3 & entries.getSlotId() != 5) {
                    logEntriesList.add(entries);
                }
            }
        } else {
            for (FoodLogEntries entries : foodLogEntries) {
                if (entries.getSlotId() == number) {
                    logEntriesList.add(entries);
                }
            }
        }
        FoodLogEntries[] logEntrieses = new FoodLogEntries[logEntriesList.size()];
        return logEntriesList.toArray(logEntrieses);
    }

    private Bundle dataToBundle(FoodLogEntries[] logEntries) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_DETAIL, HelpUtils.getJsonString(logEntries));
        return bundle;
    }

    public void setDataChangeListener(IDataChangeListener dataChangeListener) {
        this.dataChangeListener = dataChangeListener;
    }

    private class CalendarCaldroidListener extends MealCalendarCaldroidListener {
        public CalendarCaldroidListener(BaseActivity baseActivity) {
            super(baseActivity);
        }

        @Override
        public void onSelectDate(Date date, View view) {
            if (getArguments() != null) {
                getArguments().putLong(HelpUtils.MEAL_LOG_CURRENT_DATE_BUNDLE_KEY, date.getTime());
            }
            if (dataChangeListener != null) {
                dataChangeListener.onChanged(date);
            }
            getTransitManager().back();
        }
    }

    private class TodayFoodLogListener extends BaseRemoteListener<FoodLogCurrent> {
        protected TodayFoodLogListener(FragmentActivity activity) {
            super(activity);
        }

        @Override
        public void onSuccess(FoodLogCurrent result) {
            //todo need refactor because changed request
            if (result != null && result.getLogEntryList() != null && result.getLogEntryList().size() != 0) {
                foodLogEntries = result.getLogEntryList();
            } else {
                foodLogEntries = new ArrayList<FoodLogEntries>();
            }
            updateView();
        }
    }
}
