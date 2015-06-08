package com.Cadient.QMobile.view.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.Cadient.QMobile.R;
import com.Cadient.QMobile.TheApplication;
import com.Cadient.QMobile.handler.BaseHandler;
import com.Cadient.QMobile.handler.BaseRemoteListener;
import com.Cadient.QMobile.model.remote.DailyCalorieRemote;
import com.Cadient.QMobile.model.remote.WeightTrackerEntries;
import com.Cadient.QMobile.model.remote.WeightTrackerEntry;
import com.Cadient.QMobile.utils.HelpUtils;
import com.Cadient.QMobile.view.activity.MainActivity;
import com.Cadient.QMobile.view.fragment.exercisetrack.ExerciseTrackingFragment;
import com.Cadient.QMobile.view.fragment.mealtrack.MealTrackingFragment;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * Created by alexey on 13.08.14.
 */
public class DashboardFragment extends AbstractFragment<MainActivity> implements View.OnClickListener {

    private List<RetrofitSpiceRequest> requestList = new ArrayList<RetrofitSpiceRequest>();
    private List<WeightTrackerEntry> entryList = new ArrayList<WeightTrackerEntry>();
    private Calendar calendar;
    private BigDecimal startWieght;
    private BigInteger targetCalories = BigInteger.ZERO;

    ViewHolder holder;

    static class ViewHolder {
        public TextView dayNumber, dayOfWeek, month,
                targetCaloriesTextView, consumedCaloriesTextView,
                burnedCaloriesTextView, underCaloriesTextView,
                headerUnderCaloriesTextView,
                startingWeight, currentWeight, goalWeight;
        public ImageView arrowLeft, arrowRight;
        public Button btnFoodLog, btnActivityLog, btnWeight;
    }

    @Override
    protected int getViewLayout() {
        return R.layout.fragm_dashboard;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calendar = Calendar.getInstance();
    }

    @Override
    protected void assignViews(View view) {
        holder = new ViewHolder();
        holder.dayNumber = (TextView) view.findViewById(R.id.number_of_day);
        holder.dayOfWeek = (TextView) view.findViewById(R.id.day_of_week);
        holder.month = (TextView) view.findViewById(R.id.month_year);

        holder.arrowLeft = (ImageView) view.findViewById(R.id.arrow_left);
        holder.arrowRight = (ImageView) view.findViewById(R.id.arrow_right);

        holder.btnFoodLog = (Button) view.findViewById(R.id.btn_food_log);
        holder.btnActivityLog = (Button) view.findViewById(R.id.btn_activity_log);
        holder.btnWeight = (Button) view.findViewById(R.id.btn_weight);

        holder.targetCaloriesTextView = (TextView) view.findViewById(R.id.target_calories);
        holder.consumedCaloriesTextView = (TextView) view.findViewById(R.id.consumed_calories);
        holder.burnedCaloriesTextView = (TextView) view.findViewById(R.id.burned_calories);
        holder.underCaloriesTextView = (TextView) view.findViewById(R.id.under_calories);
        holder.headerUnderCaloriesTextView = (TextView) view.findViewById(R.id.header_of_under_calories);

        holder.startingWeight = (TextView) view.findViewById(R.id.starting_weight);
        holder.currentWeight = (TextView) view.findViewById(R.id.current_weight);
        holder.goalWeight = (TextView) view.findViewById(R.id.goal_weight);
    }


    @Override
    protected void initView(View view) {
        calendar.getTimeInMillis();
        updateViews(calendar);
        initSelectDayEngine();
        view.findViewById(R.id.footer).setBackgroundResource(android.R.color.white);
        holder.btnActivityLog.setOnClickListener(this);
        holder.btnFoodLog.setOnClickListener(this);
        holder.btnWeight.setOnClickListener(this);
        getBaseActivity().getDataManager().retrieveDailyCalorieBank(calendar.getTime(), calendar.getTime(),
                new BaseHandler<DailyCalorieRemote>(new DailyCalorieBankRemoteListener(getActivity()), DailyCalorieRemote.class));
        getBaseActivity().getDataManager().readWeightTrackerEntry(
                new BaseHandler<WeightTrackerEntries>(new BaseRemoteListener<WeightTrackerEntries>(getActivity()) {
                    @Override
                    public void onSuccess(WeightTrackerEntries result) {
                        entryList = result.getWeightTrackerEntries();
                        WeightTrackerEntry currentEntry = HelpUtils.findWeightEntryLastLogged(entryList);
                        startWieght = result.getProfileWeightInPounds();
                        holder.currentWeight.setText(currentEntry == null
                                ? HelpUtils.roundBigDecimal(startWieght, 1)
                                : HelpUtils.roundBigDecimal(currentEntry.getWeightInPounds(), 1));
                        holder.startingWeight.setText(HelpUtils.roundBigDecimal(startWieght, 1));
                        holder.goalWeight.setText(HelpUtils.roundBigDecimal(HelpUtils.findWeightEntryByGoal(entryList), 1));
//                        holder.goalWeight.setText(HelpUtils.roundBigDecimal(startWieght, 1));
                        updateViews(calendar);

                    }
                }, WeightTrackerEntries.class));
    }

//    private WeightTrackerEntry findWeightEntryByDay(Calendar calendar) {
//        WeightTrackerEntry result = null;
//        for (WeightTrackerEntry entry : entryList) {
//            if (HelpUtils.compareDateByDay(entry.getConvertDate().getTime(), calendar.getTimeInMillis())) {
//                result = entry;
//                break;
//            }
//        }
//        return result;
//    }

    private void updateViews(Calendar calendar) {
        holder.dayOfWeek.setText(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US));
        holder.dayNumber.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        holder.month.setText(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) + " " + String.valueOf(calendar.get(Calendar.YEAR)));
        holder.arrowRight.setVisibility(!HelpUtils.compareDateByDay(Calendar.getInstance().getTimeInMillis(), calendar.getTimeInMillis()) ? View.VISIBLE : View.INVISIBLE);
        holder.arrowLeft.setVisibility(!HelpUtils.compareDateByDay(TheApplication.getInstance().getRegistrationDate().getTime(), calendar.getTimeInMillis()) ? View.VISIBLE : View.INVISIBLE);
//        WeightTrackerEntry entry = findWeightEntryByDay(calendar);
//        if (entry != null && entry.getWeightInPounds() != null) {
//            holder.currentWeight.setText(entry.getWeightInPounds().remainder(new BigDecimal("1.0")).signum() == 0 ?
//                    entry.getWeightInPounds().toBigInteger().toString() : HelpUtils.round(entry.getWeightInPounds().abs(), 1).remainder(new BigDecimal("1.0")).signum() == 0
//                    ? entry.getWeightInPounds().abs().toBigInteger().toString() :
//                    HelpUtils.round(entry.getWeightInPounds().abs(), 1).remainder(new BigDecimal("1.0")).toString());
//        } else {
//            WeightTrackerEntry lastEntry = HelpUtils.findWeightEntryLastLogged(entryList);
//            if (lastEntry != null && lastEntry.getWeightInPounds() != null) {
//                holder.currentWeight.setText(lastEntry.getWeightInPounds().remainder(new BigDecimal("1.0")).signum() == 0 ?
//                        lastEntry.getWeightInPounds().toBigInteger().toString()
//                        : HelpUtils.round(lastEntry.getWeightInPounds().abs(), 1).toString());
//            } else if (startWieght != null) {
//                holder.currentWeight.setText(startWieght.toString());
//            }
//        }
    }

    private long lastClickedLeft = -1;
    private long lastClickedRight = -1;

    private void initSelectDayEngine() {
        holder.arrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastClickedLeft != -1 && System.currentTimeMillis() - lastClickedLeft < 300)
                    return;
                lastClickedLeft = System.currentTimeMillis();
                setDefaultDesignOfCalorieTable();
                calendar.add(Calendar.DATE, -1);
                updateViews(calendar);
                getData(calendar.getTime(), calendar.getTime());
            }
        });
        holder.arrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastClickedRight != -1 && System.currentTimeMillis() - lastClickedRight < 300)
                    return;
                lastClickedRight = System.currentTimeMillis();
                setDefaultDesignOfCalorieTable();
                calendar.add(Calendar.DATE, 1);
                updateViews(calendar);
                getData(calendar.getTime(), calendar.getTime());
            }
        });
    }

    private void getData(Date start, Date end) {
        HelpUtils.cancelAllRequests(getBaseActivity(), requestList);
        requestList.add(getBaseActivity().getDataManager().retrieveDailyCalorieBank(start, end,
                new BaseHandler<DailyCalorieRemote>(new DailyCalorieBankRemoteListener(getActivity()), DailyCalorieRemote.class)));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getHeaderController() != null) {
            getHeaderController().setTitle(getResources().getString(R.string.dashboard));
            getHeaderController().setLeftButton(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleDrawer();
                }
            }, true, R.drawable.together);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_food_log:
                getTransitManager().switchBranch(MealTrackingFragment.class);
                break;
            case R.id.btn_activity_log:
                getTransitManager().switchBranch(ExerciseTrackingFragment.class);
                break;
            case R.id.btn_weight:
                getTransitManager().switchBranch(WeightTrackingFragment.class);
                break;
        }
    }

    private void setDefaultDesignOfCalorieTable() {
        holder.headerUnderCaloriesTextView.setBackgroundResource(R.drawable.border_of_table_field_blue);
        holder.headerUnderCaloriesTextView.setText(getBaseActivity().getResources().getString(R.string.table_under));
        holder.targetCaloriesTextView.setText(targetCalories.toString());
        holder.consumedCaloriesTextView.setText(getResources().getString(R.string.table_init_calories));
        holder.burnedCaloriesTextView.setText(getResources().getString(R.string.table_init_calories));
        holder.underCaloriesTextView.setText(getResources().getString(R.string.table_init_calories));
    }

    private class DailyCalorieBankRemoteListener extends BaseRemoteListener<DailyCalorieRemote> {
        protected DailyCalorieBankRemoteListener(FragmentActivity activity) {
            super(activity);
        }

        @Override
        public void onSuccess(DailyCalorieRemote result) {
            BigInteger consumed = BigInteger.ZERO;
            BigInteger burned = BigInteger.ZERO;
            if (result.getDailyCalorieList() != null && !result.getDailyCalorieList().isEmpty()) {
                BigInteger target = HelpUtils.roundToBigInteger(result.getDailyCalorieList().get(0).getTargetCalories());
                consumed = HelpUtils.roundToBigInteger(result.getDailyCalorieList().get(0).getCaloriesConsumed());
                burned = HelpUtils.roundToBigInteger(result.getDailyCalorieList().get(0).getCaloriesBurned());
                targetCalories = (target.compareTo(BigInteger.ZERO) == 0 ? targetCalories : target);
                holder.targetCaloriesTextView.setText(targetCalories.toString());
                holder.consumedCaloriesTextView.setText(consumed.toString());
                holder.burnedCaloriesTextView.setText(burned.toString());
            }
            BigInteger overOrUnder = targetCalories.subtract(consumed).add(burned);
            holder.headerUnderCaloriesTextView.setBackgroundResource(overOrUnder.compareTo(BigInteger.ZERO) >= 0 ?
                    R.drawable.border_of_table_field_blue : R.drawable.border_of_table_field_red);
            holder.headerUnderCaloriesTextView.setText(overOrUnder.compareTo(BigInteger.ZERO) >= 0
                    ? getBaseActivity().getResources().getString(R.string.table_under) : getBaseActivity().getResources().getString(R.string.table_over));
            holder.underCaloriesTextView.setText(overOrUnder.abs().toString());
        }
    }
}
