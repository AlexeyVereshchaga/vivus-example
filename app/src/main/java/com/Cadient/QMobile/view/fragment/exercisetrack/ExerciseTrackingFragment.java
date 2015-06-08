package com.Cadient.QMobile.view.fragment.exercisetrack;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Cadient.QMobile.R;
import com.Cadient.QMobile.handler.BaseHandler;
import com.Cadient.QMobile.handler.BaseRemoteListener;
import com.Cadient.QMobile.model.Activity;
import com.Cadient.QMobile.model.remote.ActivitiesList;
import com.Cadient.QMobile.model.remote.ActivityLogEntries;
import com.Cadient.QMobile.utils.CustomTextWatcher;
import com.Cadient.QMobile.utils.GAUtils;
import com.Cadient.QMobile.utils.HelpUtils;
import com.Cadient.QMobile.view.activity.BaseActivity;
import com.Cadient.QMobile.view.activity.MainActivity;
import com.Cadient.QMobile.view.adapter.IDeleteItemListener;
import com.Cadient.QMobile.view.adapter.SearchExerciseAdapter;
import com.Cadient.QMobile.view.adapter.ViewAllAdapter;
import com.Cadient.QMobile.view.dialog.ConfirmDialog;
import com.Cadient.QMobile.view.fragment.AbstractFragment;
import com.Cadient.QMobile.view.fragment.CalendarFragment;
import com.Cadient.QMobile.view.fragment.IDataChangeListener;
import com.Cadient.QMobile.view.fragment.listener.NotifyCalendarListener;
import com.Cadient.QMobile.view.fragment.listener.RightDrawableOnTouchListener;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.roomorama.caldroid.CaldroidListener;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by ivan on 10/7/14.
 */
public class ExerciseTrackingFragment extends AbstractFragment<MainActivity> {

    private static final String TAG = ExerciseTrackingFragment.class.getSimpleName();
    private static final int MAX_NUMBER_OF_ITEMS = 3;
    private CalendarFragment calendarFragment;
    private RetrofitSpiceRequest request;
    private ViewAllAdapter allAdapter;
    private SearchExerciseAdapter exerciseAdapter;
    private Activity selectedActivity;
    private String searchStr;
    private Calendar calendarLocal;

    private TextView viewAll;
    private LinearLayout llList;
    private ListView listSearch;
    private EditText search;
    private ImageButton cleanSearch;
    private RelativeLayout rlCurrentExercise;
    private LinearLayout llUnitNumbers;
    private Button btnLog;
    private EditText etDuration;
    private EditText etDistance;
    private EditText etSteps;

    @Override
    protected int getViewLayout() {
        return R.layout.fragm_exercise_track;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sendActivitiesListRequest();
    }

    @Override
    protected void assignViews(View view) {
        llList = (LinearLayout) view.findViewById(R.id.ll_list);
        viewAll = (TextView) view.findViewById(R.id.tv_view_all);
        listSearch = (ListView) view.findViewById(R.id.list);
        search = (EditText) view.findViewById(R.id.et_search);
        cleanSearch = (ImageButton) view.findViewById(R.id.btn_clean_search);
        rlCurrentExercise = (RelativeLayout) view.findViewById(R.id.rl_current_exercise);
        llUnitNumbers = (LinearLayout) view.findViewById(R.id.ll_unit_numbers);
        btnLog = (Button) view.findViewById(R.id.btn_login);
        etDuration = (EditText) view.findViewById(R.id.et_duration);
        etDistance = (EditText) view.findViewById(R.id.et_distance);
        etSteps = (EditText) view.findViewById(R.id.et_steps);
    }

    @Override
    protected void initView(View view) {
        if (allAdapter == null) {
            allAdapter = new ViewAllAdapter(getActivity(), R.layout.universal_list_item);
        }
        if (!allAdapter.isEmpty()) {
            llList.removeAllViews();
            for (int i = 0; i < ((allAdapter.getCount() < MAX_NUMBER_OF_ITEMS) ? allAdapter.getCount() : MAX_NUMBER_OF_ITEMS); i++) {
                llList.addView(allAdapter.getView(i, null, null));
            }
            viewAll.setVisibility(allAdapter.getCount() > MAX_NUMBER_OF_ITEMS ? View.VISIBLE : View.GONE);
        }
        allAdapter.setDeleteItemListener(new DeleteItemListener());

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long dateInMillis = getBaseActivity().getCalendar().getTimeInMillis();
                Bundle bundle = new Bundle();
                bundle.putLong(HelpUtils.EXERCISE_DATE_BUNDLE_KEY, dateInMillis);
                ViewAllFragment fragment = new ViewAllFragment();
                fragment.setDataChangeListener(new IDataChangeListener() {
                    @Override
                    public void onChanged(Date date) {
                        if (date.equals(getBaseActivity().getCalendar().getTime())) {
                            sendActivitiesListRequest();
                        }
                    }
                });
                fragment.setArguments(bundle);
                FragmentTransaction t = getBaseActivity().getSupportFragmentManager().beginTransaction();
                t.replace(R.id.fragment_container, fragment);
                t.addToBackStack("0");
                t.commit();
//                getTransitManager().switchFragment(ViewAllFragment.class, bundle);
            }
        });

        listSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        if (exerciseAdapter == null) {
            exerciseAdapter = new SearchExerciseAdapter(getActivity(), R.layout.exercise_track_search_item);
        }
        exerciseAdapter.registerCallback(new SearchExerciseAdapter.Callback() {
            @Override
            public void notifyDataSetChanged() {
                setVisibilityOfViews(exerciseAdapter.isEmpty() ? View.VISIBLE : View.GONE);
            }
        });
        listSearch.setOnItemClickListener(new ItemClickListener());

        cleanSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo change to only needed requests
                getBaseActivity().getSpiceManager().cancelAllRequests();
                exerciseAdapter.clear();
                InputMethodManager imm = (InputMethodManager) getBaseActivity().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
            }
        });
        search.addTextChangedListener(new SearchTextWatcher());
        search.setOnTouchListener(new RightDrawableOnTouchListener(search) {
            @Override
            public boolean onDrawableTouch(final MotionEvent event) {
                search.getText().clear();
                selectedActivity = null;
                searchStr = null;
                event.setAction(MotionEvent.ACTION_CANCEL);
                return false;
            }
        });

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedActivity != null) {
                    HelpUtils.hideKeyBoard(getActivity());
                    logActivity(etDuration.getText().toString());
                } else {
                    Crouton.makeText(getActivity(), getResources().getString(R.string.exercise_track_error_activity_empty_message), Style.ALERT).show();
                }
            }

            private void logActivity(String numberStr) {
                int result = HelpUtils.validatePositiveNumber(numberStr);

                if (result == 0) {
                    ActivityLogEntries entries = new ActivityLogEntries();
                    entries.setActivityId(selectedActivity.getId());
                    entries.setDuration(new BigInteger(etDuration.getText().toString()));
                    entries.setMiles(
                            etDistance.getText().toString().isEmpty() ? null : new BigInteger(etDistance.getText().toString()));
                    entries.setSteps(
                            etSteps.getText().toString().isEmpty() ? null : new BigInteger(etSteps.getText().toString()));
                    entries.setDate(HelpUtils.dateToInteger(getBaseActivity().getCalendar().getTime()));
                    getBaseActivity().getDataManager().createActivityLogEntries(entries,
                            new BaseHandler<ActivityLogEntries>(new BaseRemoteListener<ActivityLogEntries>(getActivity()) {
                                @Override
                                public void onSuccess(ActivityLogEntries result) {
                                    GAUtils.trackEvent(GAUtils.ADD_ACTIVITY_ACTION);
                                    if (result.getDate().equals(HelpUtils.dateToInteger(getBaseActivity().getCalendar().getTime()))) {
                                        sendActivitiesListRequest();
                                    }
                                }
                            }, ActivityLogEntries.class));
                    clearFields();
                } else {
                    Crouton.makeText(getActivity(), getResources().getString(result), Style.ALERT).show();
                }
            }

            private void clearFields() {
                search.getText().clear();
                selectedActivity = null;
                searchStr = null;
                etDuration.getText().clear();
                etDistance.getText().clear();
                etSteps.getText().clear();
            }
        });

        listSearch.setAdapter(exerciseAdapter);
    }

    private void setVisibilityOfViews(Integer visibility) {
        if (getBaseActivity().getTransitManager().getCurrentFragment() == this) {
            if (getHeaderController() != null) {
                getHeaderController().getGeneralHeaderView().setVisibility(visibility);
            }
            cleanSearch.setVisibility(visibility == View.GONE ? View.VISIBLE : View.GONE);
            rlCurrentExercise.setVisibility(visibility);
            llUnitNumbers.setVisibility(visibility);
            btnLog.setVisibility(visibility);
        }
    }

    private void sendActivitiesListRequest() {
        if (request != null) {
            getBaseActivity().getSpiceManager().cancel(request);
        }
        request = getBaseActivity().getDataManager().getActivitiesList(
                getBaseActivity().getCalendar().getTime(), new BaseHandler<ActivitiesList>(
                        new ActivitiesRemoteListener(getActivity()), ActivitiesList.class)
        );
    }

    @Override
    public void onResume() {
        super.onResume();
        if (calendarLocal != null) {
            getBaseActivity().setCalendar(calendarLocal);
        }
        initHeader();
        exerciseAdapter.notifyDataSetChanged();
        allAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        calendarLocal = getBaseActivity().getCalendar();
    }

    private void initHeader() {
        if (getHeaderController() != null) {
            getHeaderController().setLeftButton(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleDrawer();
                }
            }, true, R.drawable.together);

            getHeaderController().setRightButton(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initCalendar();
                }
            }, true, R.drawable.exercise_calendar);

            getHeaderController().setArrowLeft(new ArrowListener(getBaseActivity()), true);
            getHeaderController().setArrowRight(new ArrowListener(getBaseActivity()), true);
        }
    }

    private class ArrowListener extends NotifyCalendarListener {

        protected ArrowListener(BaseActivity activity) {
            super(activity);
        }

        @Override
        public void onClick(View v) {
            super.onClick(v);
            allAdapter.clear();
            viewAll.setVisibility(View.GONE);
            llList.removeAllViews();
            sendActivitiesListRequest();
        }
    }

    private void initCalendar() {
        calendarFragment = HelpUtils.constructCalendarFragment(getBaseActivity().getCalendar(),
                new CalendarCaldroidListener());
        FragmentTransaction t = getBaseActivity().getSupportFragmentManager().beginTransaction();
        t.replace(R.id.fragment_container, calendarFragment);
        t.addToBackStack("0");
        t.commit();
    }

    private class ItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Activity activity = null;
            try {
                activity = (Activity) listSearch.getItemAtPosition(position);
            } catch (Exception e) {
                Log.e(TAG, "Error: " + e.getMessage(), e);
            }
            selectedActivity = activity;
            if (activity != null) {
                searchStr = activity.getName();
                search.setText(searchStr);
            }
            exerciseAdapter.clear();
        }
    }

    private class CalendarCaldroidListener extends CaldroidListener {
        private RetrofitSpiceRequest request;

        @Override
        public void onChangeMonth(int month, int year) {
            if (request != null) {
                getBaseActivity().getSpiceManager().cancel(request);
            }
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1);
            calendar.set(Calendar.DATE, 1);

            this.request = getBaseActivity().getDataManager().getActivitiesList(calendar.getTime(),
                    new BaseHandler<ActivitiesList>(new ActivitiesCalendarRemoteCallbackListener(getActivity()), ActivitiesList.class));
        }

        @Override
        public void onSelectDate(Date date, View view) {
            calendarLocal.setTime(date);
            llList.removeAllViews();
            allAdapter.clear();
            getTransitManager().back();
            sendActivitiesListRequest();
        }

        @Override
        public void onCaldroidViewCreated() {
            HelpUtils.constructCalendarWeekdayHeader(getActivity(), calendarFragment);
        }
    }

    private class ActivitiesRemoteListener extends BaseRemoteListener<ActivitiesList> {
        protected ActivitiesRemoteListener(FragmentActivity activity) {
            super(activity);
        }

        @Override
        public void onSuccess(ActivitiesList result) {
            allAdapter.clear();
            llList.removeAllViews();
            List<ActivityLogEntries> entriesList = result.getActivityLogEntriesList();
            Collections.reverse(entriesList);
            Integer numberOfItems = entriesList.size();
            allAdapter.addAll(entriesList);
            for (int i = 0; i < ((allAdapter.getCount() < MAX_NUMBER_OF_ITEMS) ? allAdapter.getCount() : MAX_NUMBER_OF_ITEMS); i++) {
                llList.addView(allAdapter.getView(i, null, null));
            }
            viewAll.setVisibility(numberOfItems > MAX_NUMBER_OF_ITEMS ? View.VISIBLE : View.GONE);
        }
    }

    private class ActivitiesCalendarRemoteCallbackListener extends BaseRemoteListener<ActivitiesList> {
        protected ActivitiesCalendarRemoteCallbackListener(FragmentActivity activity) {
            super(activity);
        }

        @Override
        public void onSuccess(ActivitiesList result) {
            List<String> datesList = result.getDateList();
            HashMap<Date, Integer> dateIntegerMap = new HashMap<Date, Integer>();
            String currentDate = HelpUtils.parseDateToString(Calendar.getInstance().getTime(), HelpUtils.DATE_FORMAT);
            for (String dateStr : datesList) {
                if (!dateStr.equals(currentDate)) {
                    dateIntegerMap.put(HelpUtils.parseStringToDate(dateStr), R.drawable.calendar_activity_date);
                }
            }
            calendarFragment.setBackgroundResourceForDates(dateIntegerMap);
            boolean compareDay = HelpUtils.compareDateByDay(getBaseActivity().getCalendar().getTimeInMillis(), Calendar.getInstance().getTimeInMillis());
            calendarFragment.setBackgroundResourceForDate(compareDay
                    ? R.drawable.calendar_current_date : R.drawable.calendar_selected_date, getBaseActivity().getCalendar().getTime());
            calendarFragment.refreshView();
        }
    }

    private class DeleteItemListener implements IDeleteItemListener<ViewAllAdapter, ActivityLogEntries> {

        @Override
        public void deleteItem(final ViewAllAdapter adapter, final ActivityLogEntries entries, View view) {
            ConfirmDialog.showDlg(getActivity(), getActivity().getString(R.string.confirm_dialog_activity_txt)).setOnDialogCloseListener(new ConfirmDialog.OnDialogCloseListener() {
                @Override
                public void OnDialogClose(ConfirmDialog.ConfirmDialogResult result) {
                    if (result == ConfirmDialog.ConfirmDialogResult.DELETE) {
                        llList.removeAllViews();
                        adapter.remove(entries);
                        viewAll.setVisibility(allAdapter.getCount() > MAX_NUMBER_OF_ITEMS ? View.VISIBLE : View.GONE);
                        for (int i = 0; i < ((adapter.getCount() < MAX_NUMBER_OF_ITEMS) ? adapter.getCount() : MAX_NUMBER_OF_ITEMS); i++) {
                            llList.addView(adapter.getView(i, null, null));
                        }
                        getBaseActivity().getDataManager().deleteActivityLogEntries(entries,
                                new BaseHandler<ActivityLogEntries>(new BaseRemoteListener(getActivity()) {
                                }, ActivityLogEntries.class)
                        );
                    }
                }
            });
        }
    }

    private class SearchTextWatcher extends CustomTextWatcher {
        private RetrofitSpiceRequest request;

        @Override
        public void afterTextChanged(final Editable s) {
            search.setCompoundDrawablesWithIntrinsicBounds(R.drawable.exercise_search, 0, s.toString().isEmpty() ? 0 : R.drawable.close_small, 0);

            if (searchStr != null && !searchStr.equals(s.toString())) {
                searchStr = null;
                selectedActivity = null;
            }
            if (!s.toString().isEmpty() && searchStr == null) {
                String firstCharsOfExercise = s.toString();
                if (request != null) {
                    getBaseActivity().getSpiceManager().cancel(request);
                }
                this.request = getBaseActivity().getDataManager().suggestActivity(
                        firstCharsOfExercise, new BaseHandler<Activity[]>(new BaseRemoteListener<Activity[]>(getActivity()) {
                            @Override
                            public void onSuccess(Activity[] result) {
                                exerciseAdapter.clear();
                                exerciseAdapter.addAll(result);
                            }
                        }, Activity[].class)
                );
            }
        }
    }
}

